package mi.m4x.project.circuitverse;

import mi.m4x.project.circuitverse.engine.Constants;
import mi.m4x.project.circuitverse.engine.graphics.Shader;
import mi.m4x.project.circuitverse.engine.io.Input;
import mi.m4x.project.circuitverse.engine.io.Window;

public class CircuitVerse implements Runnable {

    public static CircuitVerse instance;
    private Window window;
    private Shader shader;
    public void run() {
        try {
            init();
        } catch (Exception e) {
            System.err.print("Could not initialize CircuitVerse");
            e.printStackTrace();
            System.exit(-1);
        }

        double time = (double)System.currentTimeMillis() / 1000d;
        while (!window.shouldClose() && Input.isKeyDow(GLFW.GLFW_KEY_ESCAPE)) {
            if (System.currentTimeMillis() / 1000d > time + (1f / 220f)) {
                time = (double)System.currentTimeMillis() / 1000d;
                update();
                update();
            }
        }
    }

    public void init() throws Exception {

        try {
            shader = new Shader(
                    Constants.Shaders.VERTEX_SHADER,
                    Constants.Shaders.FRAGMENT_SHADER
            );
        } catch (Exception e) {
            System.err.print("Could not create shaders");
            e.printStackTrace();
            System.exit(-1);
        }

        renderer = new Renderer(window, shader);
        window.SetColor(Constants.Windows.BackgroundColor);
        window.Create();
        window.SetWindowIcon("resources/Icon.png");

        shader.Create();
    }

    public static CircuitVerse getInstance() {
        return instance;
    }
}