package engine.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

import engine.Math.Vector.Vector2;
import engine.Math.Vector.Vector3;
import engine.Math.Vector.Vector4;

public class Mesh {
	private Vertex[] vertices;
	private int[] indices;
	private int vao, pbo, ibo;
	
	public static Mesh getRectMesh() {
		Mesh returnMesh =  new Mesh(new Vertex[] { 
				new Vertex(new Vector3(-0.5f, 0.5f, 0.0f), Vector2.zero(), Vector4.zero()), 
				new Vertex(new Vector3(0.5f, 0.5f, 0.0f), Vector2.zero(), Vector4.zero()), 
				new Vertex(new Vector3(0.5f, -0.5f, 0.0f), Vector2.zero(), Vector4.zero()), 
				new Vertex(new Vector3(-0.5f, -0.5f, 0.0f), Vector2.zero(), Vector4.zero()) }, 
			new int[] { 
				0, 1, 2,
				0, 3, 2 
			}); 
		return returnMesh; 
	}
	
	public Mesh(Vertex[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
	}
	
	public void create() {
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] positionData = new float[vertices.length * 3];
		for (int i = 0; i < vertices.length; i++) {
			positionData[i * 3] = vertices[i].position.x;
			positionData[i * 3 + 1] = vertices[i].position.y;
			positionData[i * 3 + 2] = vertices[i].position.z;
		}
		positionBuffer.put(positionData).flip();
		
		pbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, pbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
		indicesBuffer.put(indices).flip();
		
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
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
}