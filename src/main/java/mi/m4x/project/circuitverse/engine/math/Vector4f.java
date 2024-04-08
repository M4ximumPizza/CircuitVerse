package mi.m4x.project.circuitverse.engine.math;

/**
 * This class is responsible for creating and destroying the vector. It is responsible
 * for setting the vector values and performing vector operations.
 *
 * @author Logan Abernathy
 */

public class Vector4f {
    private float x, y, z, w;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;

    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Vector4f add(Vector4f first, Vector4f second) {
        return new Vector4f(
                first.getX() + second.getX(),
                first.getY() + second.getY(),
                first.getZ() + second.getZ(),
                first.getW() - second.getW());
    }

    public static Vector4f subtract(Vector4f first, Vector4f second) {
        return new Vector4f(
                first.getX() - second.getX(),
                first.getY() - second.getY(),
                first.getZ() - second.getZ(),
                first.getW() - second.getW());
    }

    public static Vector4f muliply(Vector4f first, Vector4f second) {
        return new Vector4f(
                first.getX() * second.getX(),
                first.getY() * second.getY(),
                first.getZ() * second.getZ(),
                first.getW() * second.getW());
    }

    public static Vector4f divide(Vector4f first, Vector4f second) {
        return new Vector4f(
                first.getX() / second.getX(),
                first.getY() / second.getY(),
                first.getZ() / second.getZ(),
                first.getW() / second.getW());
    }

    public static float length(Vector4f vector) {
        return (float) Math.sqrt((vector.getX() * vector.getX()) + (vector.getY() * vector.getY())
                + (vector.getZ() * vector.getZ()) + (vector.getW() * vector.getW()));
    }

    public static Vector4f normalize(Vector4f vector) {
        float len = Vector4f.length(vector);
        return Vector4f.divide(vector, new Vector4f(len, len, len, len));
    }

    public static float dot(Vector4f vector1, Vector4f vector2) {
        return (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY())
                + (vector1.getZ() * vector2.getZ() + (vector1.getW() * vector2.getW()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector4f vector4f)) return false;

        if (Float.compare(vector4f.getX(), getX()) != 0) return false;
        if (Float.compare(vector4f.getY(), getY()) != 0) return false;
        if (Float.compare(vector4f.getZ(), getZ()) != 0) return false;
        return Float.compare(vector4f.getW(), getW()) == 0;
    }

    @Override
    public int hashCode() {
        int result = (getX() != +0.0f ? Float.floatToIntBits(getX()) : 0);
        result = 31 * result + (getY() != +0.0f ? Float.floatToIntBits(getY()) : 0);
        result = 31 * result + (getZ() != +0.0f ? Float.floatToIntBits(getZ()) : 0);
        result = 31 * result + (getW() != +0.0f ? Float.floatToIntBits(getW()) : 0);
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

    public void setW(float w) {
        this.w = w;
    }

    public float getW() {
        return w;
    }

}