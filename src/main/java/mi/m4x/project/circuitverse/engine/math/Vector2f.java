package mi.m4x.project.circuitverse.engine.math;

/**
 * This class is responsible for creating and destroying the vector. It is responsible
 * for setting the vector values and performing vector operations.
 *
 * @Author Logan Abernathy
 */
public class Vector2f {
    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2f add(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() + second.getX(),
                first.getY() + second.getY());
    }

    public static Vector2f subtract(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() - second.getX(),
                first.getY() - second.getY());
    }

    public static Vector2f muliply(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() * second.getX(),
                first.getY() * second.getY());
    }

    public static Vector2f divide(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() / second.getX(),
                first.getY() / second.getY());
    }

    public static float length(Vector2f vector) {
        return (float) Math.sqrt((vector.getX() * vector.getX()) + (vector.getY() * vector.getY()));
    }

    public static Vector2f normalize(Vector2f vector) {
        float len = Vector2f.length(vector);
        return Vector2f.divide(vector, new Vector2f(len, len));
    }

    public static float dot(Vector2f vector1, Vector2f vector2) {
        return (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2f vector2f)) return false;

        if (Float.compare(vector2f.getX(), getX()) != 0) return false;
        return Float.compare(vector2f.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        int result = (getX() != +0.0f ? Float.floatToIntBits(getX()) : 0);
        result = 31 * result + (getY() != +0.0f ? Float.floatToIntBits(getY()) : 0);
        return result;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

}