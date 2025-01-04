package io;

import core.impl.ScreenSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class IOUtils {

    public static int[][] readMap(String filePath, int maxRow, int maxCol) throws IOException {
        String mapPath = String.format("maps/%s.txt", filePath);
        int[][] r = new int[maxRow][maxCol];

        // Get the input stream for the resource
        InputStream inputStream = IOUtils.class.getClassLoader().getResourceAsStream(mapPath);

        // Check if the resource exists
        if (inputStream == null) {
            throw new IOException("File not found: " + mapPath);
        }

        // Read the file using BufferedReader
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int col = 0;

            while ((line = reader.readLine()) != null && col < maxCol) {

                String[] numberStrings = line.trim().split("\\s+");

                // Parse each character in the line as a row
                for (int row = 0; row < maxRow; row++) {
                    r[row][col] = Integer.parseInt(numberStrings[row]);
                }
                col++;
            }
        }
        return r;
    }

    public static BufferedImage scaleImage(BufferedImage image, int newWidth, int newHeight) {
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return newImage;
    }

    public static BufferedImage loadScaledImage(String path) {
        final int tileSize = ScreenSettings.getTileSize();
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(IOUtils.class.getClassLoader().
                    getResourceAsStream(String.format("textures/%s.png", path))));
            return scaleImage(img, tileSize, tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}