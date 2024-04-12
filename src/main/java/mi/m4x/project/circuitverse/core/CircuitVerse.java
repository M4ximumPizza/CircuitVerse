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

package mi.m4x.project.circuitverse.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import games.rednblack.miniaudio.MiniAudio;
import mi.m4x.project.circuitverse.game.Camera;
import mi.m4x.project.circuitverse.game.Chunk;
import org.lwjgl.opengl.GL20;
import org.pmw.tinylog.Logger;

public class CircuitVerse extends ApplicationAdapter {

    public static MiniAudio miniAudio;

    private Chunk chunk;
    public static Lwjgl3Application app;

    private Camera camera;

    private ModelBatch modelBatch;

    public static boolean HasFocus;

    @Override
    public void dispose() {
        super.dispose();
        miniAudio.dispose();
        Logger.info("Game Disposed");
        System.exit(0);
    }

    @Override
    public void create() {
        camera = new Camera();
        chunk = new Chunk();
        app = (Lwjgl3Application) Gdx.app;

        miniAudio = new MiniAudio();
        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setCursorCatched(true);

        modelBatch = new ModelBatch();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        this.camera.update();

        this.doTicks();

        // Update the title with the current FPS
        if (Gdx.app != null) {
            Gdx.app.getGraphics().setTitle("Circuit Verse - 1.0.0 - FPS: " + Gdx.graphics.getFramesPerSecond());
        }

        chunk.render(camera);
    }

    public float fixedUpdateAccumulator = 0f;
    public double secondsSinceLastUpdate = 0f;
    public double lastUpdateTime = 0f;

    private void doTicks() {
        this.fixedUpdateAccumulator += Gdx.graphics.getDeltaTime();
        float fixedUpdateTimestep = 0.05F;

        double curUpdateTime;
        for(curUpdateTime = (double)System.currentTimeMillis(); this.fixedUpdateAccumulator >= fixedUpdateTimestep; this.lastUpdateTime = curUpdateTime) {
            this.fixedUpdateAccumulator -= fixedUpdateTimestep;
        }

        this.secondsSinceLastUpdate = this.lastUpdateTime == -1.0 ? 0.0 : (curUpdateTime - this.lastUpdateTime) / 1000.0;
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
