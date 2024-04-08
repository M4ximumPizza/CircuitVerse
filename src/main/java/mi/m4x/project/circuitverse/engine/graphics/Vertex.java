package mi.m4x.project.circuitverse.engine.graphics;

import org.primal.engine.math.Vector2f;
import org.primal.engine.math.Vector3f;

/**
 * This class is responsible for storing the data for the mesh. It is responsible
 * for creating and destroying the mesh.
 *
 * @Author Logan Abernathy
 */

public class Vertex {
    private Vector3f position;
    private Vector2f textureCoord;

    public Vertex(Vector3f position, Vector2f textureCoord) {
        this.position = position;
        this.textureCoord = textureCoord;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector2f getTextureCoords() {
        return textureCoord;
    }

}