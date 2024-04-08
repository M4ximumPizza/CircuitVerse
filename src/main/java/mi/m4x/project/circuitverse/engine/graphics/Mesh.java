package mi.m4x.project.circuitverse.engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import org.primal.engine.math.Vector2f;
import org.primal.engine.math.Vector3f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * This class is responsible for storing the data for the mesh. It is responsible
 * for creating and destroying the mesh.
 *
 * @Author Logan Abernathy
 */

@SuppressWarnings("removal")
public class Mesh {
    private Vertex[] vertices;
    private int[] indices;
    private int vao, pbo, ibo, /*cbo,*/ tbo;
    private Material material;

    public Mesh(Vertex[] vertices, int[] indices, Material material) {
        this.vertices = vertices;
        this.indices = indices;
        this.material = material;
    }

    public void create() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            material.create();

            vao = GL30.glGenVertexArrays();
            GL30.glBindVertexArray(vao);

            // Position Buffer
            FloatBuffer positionBuffer = stack.mallocFloat(vertices.length * 3);
            for (Vertex vertex : vertices) {
                Vector3f position = vertex.getPosition();
                positionBuffer.put(position.getX()).put(position.getY()).put(position.getZ());
            }
            positionBuffer.flip();
            pbo = storeData(positionBuffer, 0, 3);

            // Texture Buffer
            FloatBuffer textureBuffer = stack.mallocFloat(vertices.length * 2);
            for (Vertex vertex : vertices) {
                Vector2f textureCoords = vertex.getTextureCoords();
                textureBuffer.put(textureCoords.getX()).put(textureCoords.getY());
            }
            textureBuffer.flip();
            tbo = storeData(textureBuffer, 1, 2);

            // Indices Buffer
            IntBuffer indicesBuffer = stack.mallocInt(indices.length);
            indicesBuffer.put(indices).flip();
            ibo = GL15.glGenBuffers();
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
            GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
            GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        }
    }

    private int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
        unbindBuffer(GL15.GL_ARRAY_BUFFER);
        return bufferID;
    }

    private void unbindBuffer(int target) {
        GL15.glBindBuffer(target, 0);
    }


    public void destroy() {
        GL15.glDeleteBuffers(pbo);
//        GL15.glDeleteBuffers(cbo);
        GL15.glDeleteBuffers(ibo);
        GL15.glDeleteBuffers(tbo);

        GL30.glDeleteVertexArrays(vao);
        material.destroy();
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public int getVAO() {
        return vao;
    }

    public int getPBO() {
        return pbo;
    }

    public int getIBO() {
        return ibo;
    }

//    public int getCBO() {
//        return cbo;
//    }

    public int getTBO() {
        return tbo;
    }

    public Material getMaterial() {
        return material;
    }
}