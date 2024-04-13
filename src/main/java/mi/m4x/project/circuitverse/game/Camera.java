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

package mi.m4x.project.circuitverse.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import mi.m4x.project.circuitverse.graphic.Matrix4f;

public class Camera {
    private PerspectiveCamera camera;
    private Vector3 position, rotation, previousPostion;
    private float moveSpeed = 0.05f, mouseSensitivity = 0.15f;
    private double oldMouseX = 0, oldMouseY = 0, currentMouseX, currentMouseY;
    private Matrix4 rotationMatrix;

    public Camera() {
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        position = new Vector3();
        this.previousPostion = new Vector3();
        rotation = new Vector3();
        camera.position.set(position);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();
    }

    public void update() {
        if (Gdx.input.isCursorCatched()) { // Check if the cursor is captured
            currentMouseX = Gdx.input.getX();
            currentMouseY = Gdx.input.getY();

            float dx = (float) (currentMouseX - oldMouseX);
            float dy = (float) (currentMouseY - oldMouseY);

            rotation.y += dx * mouseSensitivity; // horizontal rotation
            rotation.x += dy * mouseSensitivity; // vertical rotation
            rotation.x = Math.max(-90, Math.min(90, rotation.x)); // limit vertical rotation

            oldMouseX = currentMouseX;
            oldMouseY = currentMouseY;
        }

        Matrix4 rotationMatrix = new Matrix4();
        rotationMatrix.rotate(1, 0, 0, rotation.x);
        rotationMatrix.rotate(0, 1, 0, rotation.y);
        rotationMatrix.rotate(0, 0, 1, rotation.z);

        Vector3 direction = new Vector3(0, 0, -moveSpeed);
        direction.mul(rotationMatrix);

        Vector3 sideward = new Vector3(moveSpeed, 0, 0);
        sideward.mul(rotationMatrix);

        Vector3 upward = new Vector3(0, -moveSpeed, 0);
        upward.mul(rotationMatrix);

        Vector3 downward = new Vector3(0, moveSpeed, 0);
        downward.mul(rotationMatrix);

        if (Gdx.input.isKeyPressed(Input.Keys.A)) position.add(sideward); // Move left
        if (Gdx.input.isKeyPressed(Input.Keys.D)) position.sub(sideward); // Move right
        if (Gdx.input.isKeyPressed(Input.Keys.W)) position.add(direction); // Move forward
        if (Gdx.input.isKeyPressed(Input.Keys.S)) position.sub(direction); // Move backward
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) position.add(upward); // Move up
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) position.add(downward); // Move down

        camera.position.set(position);
        camera.direction.set(direction);
        camera.update();
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}
