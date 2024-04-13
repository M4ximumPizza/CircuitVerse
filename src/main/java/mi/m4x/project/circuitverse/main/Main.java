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

package mi.m4x.project.circuitverse.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowListener;
import mi.m4x.project.circuitverse.core.CircuitVerse;
import mi.m4x.project.circuitverse.game.Camera;
import mi.m4x.project.circuitverse.game.Chunk;
import mi.m4x.project.circuitverse.game.Coordinate;
import mi.m4x.project.circuitverse.graphic.Texture;
import org.pmw.tinylog.Logger;

public class Main {

    private static Camera camera;
    private static Chunk chunk;

    private static String texturePath = "textures/icon.png";;
    private static int cubeCount = 10;
    public static void main(String[] args) {
        try {
            // Initialize and start the application
            createApplication();
            Coordinate spawnPoint = new Coordinate(0, 0);
            chunk = new Chunk(texturePath, cubeCount, spawnPoint);
            camera = new Camera();
        } catch (Exception e) {
            // Handle any unexpected exceptions gracefully
            Logger.error("An error occurred during application initialization: {}", e.getMessage());
            e.printStackTrace();
            // Terminate the application
            System.exit(1);
        }
    }

    private static void createApplication() {
        try {
            // Create a new LWJGL3 application with CircuitVerse instance and configuration
            new Lwjgl3Application(new CircuitVerse(), getConfig());
        } catch (Exception e) {
            // Handle any unexpected exceptions gracefully
            Logger.error("An error occurred during application creation: {}", e.getMessage());
            e.printStackTrace();
            // Terminate the application
            System.exit(1);
        }
    }

    private static Lwjgl3ApplicationConfiguration getConfig() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Circuit Verse - 1.0.0");
        config.useVsync(true);
        config.setForegroundFPS(0);
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        config.setWindowListener(new Lwjgl3WindowListener() {
            @Override
            public void created(Lwjgl3Window lwjgl3Window) {
                // Log when the game instance is created
                Logger.info("Game Instance Created");
            }

            @Override
            public void focusLost() {
                // Set application focus status and cursor catching when focus is lost
                CircuitVerse.HasFocus = false;
                Gdx.input.setCursorCatched(false);
            }

            @Override
            public void focusGained() {
                // Set application focus status when focus is gained
                CircuitVerse.HasFocus = true;
            }

            @Override
            public boolean closeRequested() {
                // Handle the close requested event
                Gdx.app.exit();
                return false;
            }

            @Override
            public void filesDropped(String[] files) {
                // Log dropped files
                for (String file : files) {
                    Logger.info("Delicious File {} :)", file);
                }
            }

            // Implement other methods as needed, leaving them empty if not used
            @Override
            public void iconified(boolean b) {}
            @Override
            public void maximized(boolean b) {}
            @Override
            public void refreshRequested() {}
        });
        return config;
    }
}
