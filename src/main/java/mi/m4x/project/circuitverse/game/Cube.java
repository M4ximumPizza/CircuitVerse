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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import java.util.List;
import java.util.ArrayList;

public class Cube {
    private ModelInstance instance;
    private boolean isTextureLoaded;
    private List<Block> blocks;
    private Texture texture;

    public Cube(String textureFilePath, float x, float y, float z) {
        this.blocks = new ArrayList<>();
        for (int i = 0; i < 6; i++) { // A cube has 6 faces
            this.blocks.add(new Block(textureFilePath));
        }

        // Create a ModelBuilder to build the Model
        ModelBuilder modelBuilder = new ModelBuilder();

        // Create a Material to apply the texture
        Material material = new Material(TextureAttribute.createDiffuse(new Texture(textureFilePath)));

        // Create the Model
        Model model = modelBuilder.createBox(1f, 1f, 1f, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        System.out.println("Texture file path: " + textureFilePath);
        System.out.println("Material: " + material);
        System.out.println("Model: " + model);

        // Create the ModelInstance
        this.instance = new ModelInstance(model);
        this.instance.transform.setToTranslation(x,y,z);
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    public ModelInstance getInstance() {
        return instance;
    }

    public boolean isTextureLoaded() {
        return isTextureLoaded;
    }


    public void dispose() {
        instance.model.dispose();
    }

    public void resize(float scaleFactor) {
        instance.transform.scl(scaleFactor);
    }
}