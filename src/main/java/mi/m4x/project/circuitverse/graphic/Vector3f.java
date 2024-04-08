/*
 * Copyright (c) 2024, M4ximumPizza and/or its contributors. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is licensed under the PolyForm Shield License 1.0.0; you can use, modify,
 * and distribute this code for any purpose that does not compete with the software or
 * any product the licensor or any of its affiliates provides using the software.
 * M4ximumPizza designates this particular file as subject to the "Classpath" exception
 * as provided by M4ximumPizza in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the PolyForm Shield License 1.0.0 for more details
 * (a copy is included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the PolyForm Shield License 1.0.0 along with this work;
 * if not, here is the license [https://polyformproject.org/wp-content/uploads/2020/06/PolyForm-Shield-1.0.0.txt].
 */

package mi.m4x.project.circuitverse.graphic;

/**
 * This class represents a 3-dimensional vector and provides methods for vector operations.
 *
 * @Author Logan Abernathy
 */
public class Vector3f {
    private float x, y, z;

    /**
     * Constructs a new Vector3f with the specified x, y, and z components.
     *
     * @param x The x component of the vector.
     * @param y The y component of the vector.
     * @param z The z component of the vector.
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Sets the components of the vector.
     *
     * @param x The new x component.
     * @param y The new y component.
     * @param z The new z component.
     */
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Adds two vectors.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the addition.
     */
    public static Vector3f add(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() + second.getX(),
                first.getY() + second.getY(),
                first.getZ() + second.getZ());
    }

    /**
     * Subtracts one vector from another.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the subtraction.
     */
    public static Vector3f subtract(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() - second.getX(),
                first.getY() - second.getY(),
                first.getZ() - second.getZ());
    }

    /**
     * Multiplies two vectors component-wise.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the multiplication.
     */
    public static Vector3f multiply(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() * second.getX(),
                first.getY() * second.getY(),
                first.getZ() * second.getZ());
    }

    /**
     * Divides one vector by another component-wise.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the division.
     */
    public static Vector3f divide(Vector3f first, Vector3f second) {
        return new Vector3f(
                first.getX() / second.getX(),
                first.getY() / second.getY(),
                first.getZ() / second.getZ());
    }

    /**
     * Calculates the length (magnitude) of the vector.
     *
     * @param vector The vector.
     * @return The length of the vector.
     */
    public static float length(Vector3f vector) {
        return (float) Math.sqrt(
                (vector.getX() * vector.getX()) + (vector.getY() * vector.getY()) + (vector.getZ() * vector.getZ()));
    }

    /**
     * Returns a normalized version of the vector.
     *
     * @param vector The vector.
     * @return The normalized vector.
     */
    public static Vector3f normalize(Vector3f vector) {
        float len = Vector3f.length(vector);
        return Vector3f.divide(vector, new Vector3f(len, len, len));
    }

    /**
     * Computes the dot product of two vectors.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The dot product.
     */
    public static float dot(Vector3f vector1, Vector3f vector2) {
        return (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY())
                + (vector1.getZ() * vector2.getZ());
    }

    // Getters and setters

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

    // Equals and hashcode methods

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

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
}
