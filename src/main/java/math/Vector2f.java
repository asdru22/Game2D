package math;

public class Vector2f {
    public float x, y;

    public Vector2f(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    public void normalize() {
        float magnitude = this.magnitude();
        if (magnitude == 0) return; // Avoid division by zero
        x /= magnitude;
        y /= magnitude;
    }

    public void multiply(float amount) {
        x *= amount;
        y *= amount;
    }

    public float magnitude(){
        return (float) Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "Vector2f [x=" + x + ", y=" + y + "]";
    }
}
