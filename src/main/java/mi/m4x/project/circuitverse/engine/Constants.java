package mi.m4x.project.circuitverse.engine;

public class Constants {

    public static final class Shaders {
        public static final String VERTEX_SHADER = "src/main/java/mi/m4x/project/circuitverse/engine/shaders/vertex.glsl";
        public static final String FRAGMENT_SHADER = "src/main/java/mi/m4x/project/circuitverse/engine/shaders/fragment.glsl";
    }

    public static final class Windows {
        public static final int WIDTH = 1280;
        public static final int HEIGHT = 760;
        public static final String TITLE = "CircuitVerse";
        public static final String BackgroundColor = true ? "#2994f2" : "#1a001a";
    }

public static final class Textures {
        public static final String ATLAS = "src/main/java/mi/m4x/project/circuitverse/engine/textures/TextureAtlas.png";
    }

    public static final class Chunks {
        public static final int MAX_WIDTH = 16;
        public static final int MAX_HEIGHT = 1;
    }
}
