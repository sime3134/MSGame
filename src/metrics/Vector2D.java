package metrics;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int intX() {
        return (int) x;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int intY() {
        return (int) y;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void normalize() {
        double length = Math.sqrt(x * x + y * y);
        if(x != 0) {
            x = x / length;
        }
        if(y != 0) {
            y = y / length;
        }
    }

    public void multiply(double value) {
        x *= value;
        y *= value;
    }

    public void add(Vector2D vector) {
        x += vector.getX();
        y += vector.getY();
    }
}
