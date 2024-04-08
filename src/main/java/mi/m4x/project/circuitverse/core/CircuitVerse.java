package mi.m4x.project.circuitverse.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import games.rednblack.miniaudio.MiniAudio;
import org.pmw.tinylog.Logger;

public class CircuitVerse extends ApplicationAdapter {

    public static MiniAudio miniAudio;
    public static Lwjgl3Application app;

    public static boolean HasFocus;

    @Override
    public void dispose() {
        super.dispose();
        miniAudio.dispose();
        Logger.info("Game Disposed");
        System.exit(0);
    }

    public static void onInit() {

    }

    @Override
    public void create() {
        app = (Lwjgl3Application) Gdx.app;

        miniAudio = new MiniAudio();
        Gdx.graphics.setForegroundFPS(60);
        Gdx.input.setCursorCatched(true);

        onInit();
    }

    @Override
    public void render() {
        super.render();
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
