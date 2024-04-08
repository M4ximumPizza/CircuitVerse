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

import mi.m4x.project.circuitverse.graphic.Vector3f;

;

/**
 * This class is responsible for creating and manipulating 4x4 matrices.
 * It provides methods for matrix operations like identity, translation, rotation, scaling,
 * transformation, multiplication, and projection.
 *
 * @author Logan Abernathy (https://github.com/M4ximumPizza)
 */
public class Matrix4f {
    public static final int SIZE = 4;
    private float[] elements = new float[SIZE * SIZE];

    /**
     * Returns the identity matrix.
     *
     * @return The identity matrix.
     */
    public static Matrix4f identity() {
        Matrix4f result = new Matrix4f();

        // Set diagonal elements to 1
        for (int i = 0; i < SIZE; i++) {
            result.set(i, i, 1);
        }

        return result;
    }

    /**
     * Returns a translation matrix.
     *
     * @param translate The translation vector.
     * @return The translation matrix.
     */
    public static Matrix4f translate(Vector3f translate) {
        Matrix4f result = Matrix4f.identity();

        // Set translation components
        result.set(3, 0, translate.getX());
        result.set(3, 1, translate.getY());
        result.set(3, 2, translate.getZ());

        return result;
    }

    /**
     * Returns a rotation matrix.
     *
     * @param angle The rotation angle in degrees.
     * @param axis  The axis of rotation.
     * @return The rotation matrix.
     */
    public static Matrix4f rotate(float angle, Vector3f axis) {
        Matrix4f result = Matrix4f.identity();

        // Calculate trigonometric values
        float cos = (float) Math.cos(Math.toRadians(angle));
        float sin = (float) Math.sin(Math.toRadians(angle));
        float C = 1 - cos;

        // Set rotation components
        result.set(0, 0, cos + axis.getX() * axis.getX() * C);
        result.set(0, 1, axis.getX() * axis.getY() * C - axis.getZ() * sin);
        result.set(0, 2, axis.getX() * axis.getZ() * C + axis.getY() * sin);
        result.set(1, 0, axis.getY() * axis.getX() * C + axis.getZ() * sin);
        result.set(1, 1, cos + axis.getY() * axis.getY() * C);
        result.set(1, 2, axis.getY() * axis.getZ() * C - axis.getX() * sin);
        result.set(2, 0, axis.getZ() * axis.getX() * C - axis.getY() * sin);
        result.set(2, 1, axis.getZ() * axis.getY() * C + axis.getX() * sin);
        result.set(2, 2, cos + axis.getZ() * axis.getZ() * C);

        return result;
    }

    /**
     * Returns a scaling matrix.
     *
     * @param scalar The scaling vector.
     * @return The scaling matrix.
     */
    public static Matrix4f scale(Vector3f scalar) {
        Matrix4f result = Matrix4f.identity();

        // Set scaling components
        result.set(0, 0, scalar.getX());
        result.set(1, 1, scalar.getY());
        result.set(2, 2, scalar.getZ());

        return result;
    }

    /**
     * Returns a transformation matrix combining translation, rotation, and scaling.
     *
     * @param position The translation vector.
     * @param rotation The rotation vector (Euler angles).
     * @param scale    The scaling vector.
     * @return The transformation matrix.
     */
    public static Matrix4f transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        Matrix4f result = Matrix4f.identity();

        Matrix4f translationMatrix = Matrix4f.translate(position);
        Matrix4f rotXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rotYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rotZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));
        Matrix4f scaleMatrix = Matrix4f.scale(scale);

        Matrix4f rotationMatrix = Matrix4f.multiply(rotXMatrix, Matrix4f.multiply(rotYMatrix, rotZMatrix));

        result = Matrix4f.multiply(translationMatrix, Matrix4f.multiply(rotationMatrix, scaleMatrix));

        return result;
    }

    /**
     * Performs matrix multiplication.
     *
     * @param matrix The first matrix.
     * @param other  The second matrix.
     * @return The result of matrix multiplication.
     */
    public static Matrix4f multiply(Matrix4f matrix, Matrix4f other) {
        Matrix4f result = Matrix4f.identity();

        // Perform matrix multiplication
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                float sum = 0;
                for (int k = 0; k < SIZE; k++) {
                    sum += matrix.get(i, k) * other.get(k, j);
                }
                result.set(i, j, sum);
            }
        }

        return result;
    }

    /**
     * Returns a perspective projection matrix.
     *
     * @param fov    The field of view angle in degrees.
     * @param aspect The aspect ratio of the viewport (width / height).
     * @param near   The near clipping plane distance.
     * @param far    The far clipping plane distance.
     * @return The perspective projection matrix.
     */
    public static Matrix4f projection(float fov, float aspect, float near, float far) {
        Matrix4f result = Matrix4f.identity();

        float tanFOV = (float) Math.tan(Math.toRadians(fov / 2));
        float range = far - near;

        result.set(0, 0, 1.0f / (aspect * tanFOV));
        result.set(1, 1, 1.0f / tanFOV);
        result.set(2, 2, -((far + near) / range));
        result.set(2, 3, -1.0f);
        result.set(3, 2, -((2 * far * near) / range));
        result.set(3, 3, 0.0f);

        return result;
    }

    /**
     * Returns a view matrix for the given camera position and rotation.
     *
     * @param position The camera position.
     * @param rotation The camera rotation (Euler angles).
     * @return The view matrix.
     */
    public static Matrix4f view(Vector3f position, Vector3f rotation) {
        Matrix4f result = Matrix4f.identity();

        Vector3f negative = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
        Matrix4f translationMatrix = Matrix4f.translate(negative);
        Matrix4f rotXMatrix = Matrix4f.rotate(rotation.getX(), new Vector3f(1, 0, 0));
        Matrix4f rotYMatrix = Matrix4f.rotate(rotation.getY(), new Vector3f(0, 1, 0));
        Matrix4f rotZMatrix = Matrix4f.rotate(rotation.getZ(), new Vector3f(0, 0, 1));

        Matrix4f rotationMatrix = Matrix4f.multiply(rotZMatrix, Matrix4f.multiply(rotYMatrix, rotXMatrix));

        result = Matrix4f.multiply(translationMatrix, rotationMatrix);

        return result;
    }

    /**
     * Gets the element at the specified position in the matrix.
     *
     * @param x The row index.
     * @param y The column index.
     * @return The element value.
     */
    public float get(int x, int y) {
        return elements[y * SIZE + x];
    }

    /**
     * Sets the element at the specified position in the matrix.
     *
     * @param x     The row index.
     * @param y     The column index.
     * @param value The value to set.
     */
    public void set(int x, int y, float value) {
        elements[y * SIZE + x] = value;
    }

    /**
     * Gets all elements of the matrix.
     *
     * @return The array containing all elements.
     */
    public float[] getAll() {
        return elements;
    }
}
