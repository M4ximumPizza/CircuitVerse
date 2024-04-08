package mi.m4x.project.circuitverse.engine.graphics;

import mi.m4x.project.circuitverse.engine.math.Matrix4f;
import mi.m4x.project.circuitverse.engine.math.Vector2f;
import mi.m4x.project.circuitverse.engine.math.Vector3f;
import mi.m4x.project.circuitverse.engine.math.Vector4f;
import mi.m4x.project.circuitverse.utils.FileUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

/**
 * This class is responsible for creating and destroying the shader. It is
 * responsible for setting the uniform values and binding the shader.
 *
 * @Author Logan Abernathy
 */
public class Shader {
    private String VertexPath, FragmentPath, VertexFile, FragmentFile;
    private int VertexID, FragmentID, ProgramID;

    public Shader(String VertexPath, String FragmentPath) throws Exception {
        this.VertexPath = VertexPath; VertexFile = new String(FileUtils.ReadResource(this.VertexPath));
        this.FragmentPath = FragmentPath; FragmentFile = new String(FileUtils.ReadResource(this.FragmentPath));
    }

    private int ShaderCompilerAndErrChecker(int type, String File, String Path) {

        int ID = GL20.glCreateShader(type);
        GL20.glShaderSource(ID, File);
        GL20.glCompileShader(ID);

        if (GL20.glGetShaderi(ID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.err.println(ID);
            System.err.println(Path + " : " + GL20.glGetShaderInfoLog(ID));
            return ID;
        }
        return ID;
    }

    public void Create() {
        ProgramID = GL20.glCreateProgram();
        VertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

        VertexID = ShaderCompilerAndErrChecker(GL20.GL_VERTEX_SHADER, VertexFile, VertexPath);
        FragmentID = ShaderCompilerAndErrChecker(GL20.GL_FRAGMENT_SHADER, FragmentFile, FragmentPath);
        GL20.glAttachShader(ProgramID, VertexID);
        GL20.glAttachShader(ProgramID, FragmentID);

        GL20.glLinkProgram(ProgramID);
        if (GL20.glGetProgrami(ProgramID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            System.err.println("Program Linking: " + GL20.glGetProgramInfoLog(ProgramID));
            return;
        }
        GL20.glValidateProgram(ProgramID);
        if (GL20.glGetProgrami(ProgramID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
            System.err.println("Program Validation: " + GL20.glGetProgramInfoLog(ProgramID));
            return;
        }
    }

    public int getUniformLocation(String name) {
        return GL20.glGetUniformLocation(ProgramID, name);
    }

    public void setUniform(String name, float value) {
        GL20.glUniform1f(getUniformLocation(name), value);
    }

    public void setUniform(String name, int value) {
        GL20.glUniform1i(getUniformLocation(name), value);
    }

    public void setUniform(String name, boolean value) {
        GL20.glUniform1i(getUniformLocation(name), value ? 1 : 0);
    }

    public void setUniform(String name, Vector2f value) {
        GL20.glUniform2f(getUniformLocation(name), value.getX(), value.getY());
    }

    public void setUniform(String name, Vector3f value) {
        GL20.glUniform3f(getUniformLocation(name), value.getX(), value.getY(), value.getZ());
    }

    public void setUniform(String name, Vector4f value) {
        GL20.glUniform4f(getUniformLocation(name), value.getX(), value.getY(), value.getZ(), value.getW());
    }

    public void setUniform(String name, Matrix4f value) {
        FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4f.SIZE * Matrix4f.SIZE);
        matrix.put(value.getAll()).flip();
        GL20.glUniformMatrix4fv(getUniformLocation(name), true, matrix);

    }

    public void bind() {
        GL20.glUseProgram(ProgramID);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void destroy() {
        GL20.glDetachShader(ProgramID, VertexID);
        GL20.glDetachShader(ProgramID, FragmentID);
        GL20.glDeleteShader(VertexID);
        GL20.glDeleteShader(FragmentID);
        GL20.glDeleteProgram(ProgramID);
    }
}