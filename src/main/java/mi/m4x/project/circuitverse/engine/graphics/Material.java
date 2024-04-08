package mi.m4x.project.circuitverse.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.primal.utils.FileUtils;

/**
 * ResourceTexture class is responsible for loading the texture from the file.
 *
 * @author Logan Abernathy
 */

public class Material {
    private Texture texture;
    private float width, height;
    private int textureID;
    private String path;

    public Material(String path) {
        this.path = "/assets/" + path;
    }

    public void create() {

        try {
            texture = TextureLoader.getTexture(path.split("[.]")[1],
                    FileUtils.class.getResource(path).openConnection().getInputStream(),
                    GL11.GL_NEAREST);
            width = texture.getWidth();
            height = texture.getHeight();
            textureID = texture.getTextureID();
        } catch (Exception e) {
            // e.printStackTrace();
        }

    }

    public void destroy() {
        GL13.glDeleteTextures(textureID);
    }

    public int getTextureID() {
        return textureID;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}