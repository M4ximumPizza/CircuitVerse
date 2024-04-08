package mi.m4x.project.circuitverse.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Window;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowListener;
import mi.m4x.project.circuitverse.core.CircuitVerse;
import org.pmw.tinylog.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            // Initialize and start the application
            createApplication();
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
        config.setWindowedMode(1024, 576);
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
