package engine.graphics;

import engine.Math.Vector.*;

public class Vertex {
	public Vector3 position;
	public Vector2 UV;
	public Vector4 rgba;
	
	public Vertex(Vector3 position, Vector2 UV, Vector4 rgba) {
		this.position = position;
		this.UV = UV;
		this.rgba = rgba;
	}
	
	public Vertex(Vector3 position, Vector2 UV, Vector3 rgb) {
		this.position = position;
		this.UV = UV;
		this.rgba = new Vector4(rgb.x, rgb.y, rgb.z, 1);
	}
}
