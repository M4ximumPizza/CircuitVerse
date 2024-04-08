package mi.m4x.project.circuitverse.engine.math;

/**
 * This class is responsible for creating and destroying the vector. It is responsible
 * for setting the vector values and performing vector operations.
 *
 * @Author Logan Abernathy
 */
public class Vector3f {
    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f add(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() + second.getX(),
                first.getY() + second.getY(),
                first.getZ() + second.getZ());
    }

    public static Vector3f subtract(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() - second.getX(),
                first.getY() - second.getY(),
                first.getZ() - second.getZ());
    }

    public static Vector3f muliply(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() * second.getX(),
                first.getY() * second.getY(),
                first.getZ() * second.getZ());
    }

    public static Vector3f divide(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() / second.getX(),
                first.getY() / second.getY(),
                first.getZ() / second.getZ());
    }

    public static float length(Vector3f vector) {
        return (float) Math.sqrt(
                (vector.getX() * vector.getX()) + (vector.getY() * vector.getY()) + (vector.getZ() * vector.getZ()));
    }

    public static Vector3f normalize(Vector3f vector) {
        float len = Vector3f.length(vector);
        return Vector3f.divide(vector, new Vector3f(len, len, len));
    }

    public static float dot(Vector3f vector1, Vector3f vector2) {
        return (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY())
                + (vector1.getZ() * vector2.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3f vector3f)) return false;

        if (Float.compare(vector3f.getX(), getX()) != 0) return false;
        if (Float.compare(vector3f.getY(), getY()) != 0) return false;
        return Float.compare(vector3f.getZ(), getZ()) == 0;
    }

    @Override
    public int hashCode() {
        int result = (getX() != +0.0f ? Float.floatToIntBits(getX()) : 0);
        result = 31 * result + (getY() != +0.0f ? Float.floatToIntBits(getY()) : 0);
        result = 31 * result + (getZ() != +0.0f ? Float.floatToIntBits(getZ()) : 0);
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

    public void setZ(float z) {
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
}