package mi.m4x.project.circuitverse.engine.graphics;

import mi.m4x.project.circuitverse.CircuitVerse;
import mi.m4x.project.circuitverse.engine.io.Window;

public class Renderer {

    private Shader shader;
    private Window window;
    private CircuitVerse main;

    public Renderer(Shader shader, Window window) {
        this.shader = shader;
        this.window = window;
    }

    public void init() {
        this.main = CircuitVerse.getInstance();
    }
}
