package io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class IOUtils {
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(IOUtils.class.getClassLoader().
                    getResourceAsStream(String.format("textures/%s.png", path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
                // Ensure line length matches maxCol by trimming or padding with zeros
                line = line.length() > maxRow ? line.substring(0, maxRow) :
                        String.format("%-" + maxRow + "s", line).replace(' ', '0');

                // Parse each character in the line as a row
                for (int row = 0; row < maxRow; row++) {
                    r[row][col] = Character.getNumericValue(line.charAt(row));
                }
                col++;
            }
        }
        return r;
    }

}