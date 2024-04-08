package mi.m4x.project.circuitverse.engine.io;

import org.lwjgl.glfw.*;

/**
 * This class is responsible for handling the input. It is responsible for
 * getting the input from the keyboard and mouse.
 *
 * @Author Logan Abernathy
 */
public class Input {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;
    private static double scrollX, scrollY;

    private static GLFWKeyCallback keyboard;
    private static GLFWMouseButtonCallback mouseClick;
    private static GLFWCursorPosCallback mouseMove;
    private static GLFWScrollCallback mouseScroll;

    public Input() {
        keyboard = new GLFWKeyCallback() {
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };
        mouseMove = new GLFWCursorPosCallback() {
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseClick = new GLFWMouseButtonCallback() {
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseScroll = new GLFWScrollCallback() {
            public void invoke(long window, double offsetX, double offsetY) {
                scrollX += offsetX;
                scrollY += offsetY;
            }
        };
    }

    public static boolean isKeyDown(int key) {
        return keys[key];
    }

    public static boolean isButtonDown(int button) {
        return buttons[button];
    }

    public void destroy() {
        keyboard.free();
        mouseClick.free();
        mouseMove.free();
        mouseScroll.free();
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static double getScrollX() {
        return scrollX;
    }

    public static double getScrollY() {
        return scrollY;
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public GLFWCursorPosCallback getMouseMoveCallback() {
        return mouseMove;
    }

    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseClick;
    }

    public GLFWScrollCallback getMouseScrollCallback() {
        return mouseScroll;
    }

}