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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
    private List<Cube> cubes;
    private ModelBatch modelBatch;
    private Coordinate spawnPoint;

    public Chunk(String texturePath, int cubeCount, Coordinate spawnPoint) {
        this.modelBatch = new ModelBatch();
        this.cubes = new ArrayList<>();
        this.spawnPoint = spawnPoint;

        int width = 16;
        int height = 16;
        int depth = 1;

        int halfWidth = width / 2;
        int halfHeight = height / 2;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    this.cubes.add(new Cube(texturePath, x - halfWidth, y - halfHeight, z));
                }
            }
        }
    }

    public void render(Camera camera) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera.getCamera());
        for (Cube cube : cubes) {
            modelBatch.render(cube.getInstance());
        }
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
        for (Cube cube : cubes) {
            cube.dispose();
        }
    }
}