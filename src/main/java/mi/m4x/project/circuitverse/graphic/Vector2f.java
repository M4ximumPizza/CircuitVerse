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
 * This class represents a 2-dimensional vector and provides methods for vector operations.
 *
 * @Author Logan Abernathy
 */
public class Vector2f {
    private float x, y;

    /**
     * Constructs a new Vector2f with the specified x and y components.
     *
     * @param x The x component of the vector.
     * @param y The y component of the vector.
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the components of the vector.
     *
     * @param x The new x component.
     * @param y The new y component.
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds two vectors.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the addition.
     */
    public static Vector2f add(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() + second.getX(),
                first.getY() + second.getY());
    }

    /**
     * Subtracts one vector from another.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the subtraction.
     */
    public static Vector2f subtract(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() - second.getX(),
                first.getY() - second.getY());
    }

    /**
     * Multiplies two vectors component-wise.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the multiplication.
     */
    public static Vector2f multiply(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() * second.getX(),
                first.getY() * second.getY());
    }

    /**
     * Divides one vector by another component-wise.
     *
     * @param first  The first vector.
     * @param second The second vector.
     * @return The result of the division.
     */
    public static Vector2f divide(Vector2f first, Vector2f second) {
        return new Vector2f(
                first.getX() / second.getX(),
                first.getY() / second.getY());
    }

    /**
     * Calculates the length (magnitude) of the vector.
     *
     * @param vector The vector.
     * @return The length of the vector.
     */
    public static float length(Vector2f vector) {
        return (float) Math.sqrt((vector.getX() * vector.getX()) + (vector.getY() * vector.getY()));
    }

    /**
     * Returns a normalized version of the vector.
     *
     * @param vector The vector.
     * @return The normalized vector.
     */
    public static Vector2f normalize(Vector2f vector) {
        float len = Vector2f.length(vector);
        return Vector2f.divide(vector, new Vector2f(len, len));
    }

    /**
     * Computes the dot product of two vectors.
     *
     * @param vector1 The first vector.
     * @param vector2 The second vector.
     * @return The dot product.
     */
    public static float dot(Vector2f vector1, Vector2f vector2) {
        return (vector1.getX() * vector2.getX()) + (vector1.getY() * vector2.getY());
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

    // Equals and hashcode methods

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
}
