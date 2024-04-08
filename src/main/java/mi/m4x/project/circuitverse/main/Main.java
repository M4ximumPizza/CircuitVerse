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
        Logger.info("Hello World");
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new CircuitVerse(), getConfig());
    }

    private static Lwjgl3ApplicationConfiguration getConfig() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Circuit Verse - 1.0.0");
        config.useVsync(true);
        config.enableGLDebugOutput(true, System.err);
        config.setForegroundFPS(0);
        config.setWindowedMode(1024, 576);
        config.setWindowListener(new Lwjgl3WindowListener() {
            @Override
            public void created(Lwjgl3Window lwjgl3Window) {
                Logger.info("Game Instance Created");
            }
            @Override
            public void iconified(boolean b) {}
            @Override
            public void maximized(boolean b) {}

            @Override
            public void focusLost() {
                CircuitVerse.HasFocus = false;
                Gdx.input.setCursorCatched(false);
            }

            @Override
            public void focusGained() {
                CircuitVerse.HasFocus = true;
            }

            @Override
            public boolean closeRequested() {
                return false;
            }

            @Override
            public void filesDropped(String[] files) {
                for (String file : files) {
                    Logger.info("Delicious File %s :)".formatted(file));
                }
            }

            @Override
            public void refreshRequested() {}
        });
        return config;
    }

}
