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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Chunk {
    private static final int SIZE = 16;
    private Cube[][][] cubes = new Cube[SIZE][SIZE][1];
    private Vector3 cubeSize;

    public Chunk() {
        Texture texture = new Texture("textures/icon.png");
        TextureRegion textureRegion = new TextureRegion(texture);
        cubeSize = new Vector3(1, 1, 1);
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < 1; z++) {
                    Vector3 position = new Vector3(x, y, z);
                    cubes[x][y][z] = new Cube(textureRegion, position);
                }
            }
        }
    }

    public void render() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < 1; z++) {
                    cubes[x][y][z].render(cubeSize);
                }
            }
        }
    }

    public void dispose() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < 1; z++) {
                    if (cubes[x][y][z] != null) {
                        cubes[x][y][z].dispose();
                    }
                }
            }
        }
    }

    public void resize(int width, int height) {
        cubeSize.x = width / (float)SIZE;
        cubeSize.y = height / (float)SIZE;
    }
}