package mi.m4x.project.circuitverse.engine.io;

import mi.m4x.project.circuitverse.CircuitVerse;
import mi.m4x.project.circuitverse.engine.math.Matrix4f;
import mi.m4x.project.circuitverse.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 * This class is responsible for creating and destroying the window. It is
 * responsible for updating the window and swapping the buffers.
 *
 * @Author Logan Abernathy
 */
public class Window {
    private int width, height;
    private int fWidth, fHeight, refreshRate;
    private String title;
    private long window;
    private int frames;
    private static long time;
    private Input input;
    private Vector3f background = new Vector3f(0, 0, 0);
    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized;
    private boolean isFullScreen;
    private boolean F11Pressed = false;
    private int[] windowPosX = new int[1], windowPosY = new int[1];
    private Matrix4f projection;

    public Window(CircuitVerse minecraft, int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        projection = Matrix4f.projection(70.0f, (float) width / (float) height, 0.1f, 1000);
    }
    
    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        projection = Matrix4f.projection(70.0f, (float) width / (float) height, 0.1f, 1000);
    }

    private GLFWVidMode GetVidMode() { return GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()); }

    private void getDisplaySize() {
        GLFWVidMode glfwGetVideoMode = GetVidMode();
        fWidth = glfwGetVideoMode.width();
        fHeight = glfwGetVideoMode.height();
        refreshRate = glfwGetVideoMode.refreshRate();
    }

    public void SetWindowIcon(String path) throws Exception {
        ImageParser resource = ImageParser.LoadResourceImage(path);
        GLFWImage image = GLFWImage.malloc();
        GLFWImage.Buffer imagebf = GLFWImage.malloc(1);
        image.set(resource.GetHeight(), resource.GetHeight(), resource.GetImage());
        imagebf.put(0, image);
        GLFW.glfwSetWindowIcon(window, imagebf);
    }

    public void DoFullscreenCheck() {
        if (Input.isKeyDown(GLFW.GLFW_KEY_F11) && !F11Pressed) {
            this.setFullScreen(!this.getIsFullScreen());
            F11Pressed = true;
        }
        if (!Input.isKeyDown(GLFW.GLFW_KEY_F11) && F11Pressed) {
            F11Pressed = false;
        }
        if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) this.mouseState(true);
    }

    public void Create() { Create(true); }

    public void Create(boolean vsync) {

        getDisplaySize();

        input = new Input();
        window = GLFW.glfwCreateWindow(width, height, title, isFullScreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if (window == 0) {
            System.err.println("ERROR: WINDOW WASN'T CREATED");
            return;
        }

        GLFWVidMode vidMode = GetVidMode();
        windowPosX[0] = (vidMode.width() - width) / 2;
        windowPosY[0] = (vidMode.height() - height) / 2;
        GLFW.glfwSetWindowPos(window, windowPosX[0], windowPosY[0]);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        createCallbacks();
        GLFW.glfwShowWindow(window);

        // Disable VSync
        GLFW.glfwSwapInterval(vsync ? 1 : 0);

        time = System.currentTimeMillis();
    }

    private void createCallbacks() {
        sizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
        GLFW.glfwSetScrollCallback(window, input.getMouseScrollCallback());
    }

    public void update() {
        if (isResized) {
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }
        GL11.glClearColor(background.getX(), background.getY(), background.getZ(), 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GLFW.glfwPollEvents();
        frames++;
        if (System.currentTimeMillis() > time + 1000) {
            GLFW.glfwSetWindowTitle(window, title + " | FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy() {
        input.destroy();
        sizeCallback.free();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void SetColor(Vector3f ColorVector) {
        SetColor(ColorVector.getX(), ColorVector.getY(), ColorVector.getZ());
    }

    public void SetColor(float r, float g, float b) {
        background.set(r / 255, g / 255, b / 255);
    }

    public void SetColor(String Hex) {
        Hex = Hex.replaceAll("#", "");
        if (Hex.length() != 6) background.set(0, 0, 0);
        float R = Integer.valueOf(Hex.substring(0, 2), 16) / 255f;
        float G = Integer.valueOf(Hex.substring(2,4), 16) / 255f;
        float B = Integer.valueOf(Hex.substring(4, 6), 16) / 255f;
        background.set(R, G, B);
    }

    @Deprecated
    public void setBackgroundColor(float r, float g, float b) {
        background.set(r, g, b);
    }

    public long getTime() {
        return time;
    }

    public float getBackgroundRed() {
        return background.getX();
    }

    public float getBackgroundGreen() {
        return background.getY();
    }

    public float getBackgroundBlue() {
        return background.getZ();
    }

    public boolean getIsResized() {
        return isResized;
    }

    public boolean getIsFullScreen() {
        return isFullScreen;
    }

    private int OldWidth = width;
    private int OldHeight = height;

    public void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        this.isResized = true;
        if (isFullScreen) {
            OldWidth = width;
            OldHeight = height;
            GLFW.glfwGetWindowPos(window, windowPosX, windowPosY);
            GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, fWidth, fHeight, refreshRate);
            projection = Matrix4f.projection(70.0f, (float) fWidth / (float) fHeight, 0.1f, 1000);
        } else {
            width = OldWidth;
            height = OldHeight;
            GLFW.glfwSetWindowMonitor(window, 0, windowPosX[0], windowPosY[0], width, height, refreshRate);
            projection = Matrix4f.projection(70.0f, (float) width / (float) height, 0.1f, 1000);
        }
    }

    public void mouseState(boolean lock) {
        GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, lock ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public long getWindow() {
        return window;
    }

    public Matrix4f getProjectionMatrix() {
        return projection;
    }

}