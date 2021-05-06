package engine.graphics;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Material {
	private Texture texture;
	private float width, height;
	private int textureID;
	
	public Material(String path) {
		try {
			texture = TextureLoader.getTexture(path.split("[.]")[1], Material.class.getResourceAsStream(path), GL11.GL_LINEAR);
		} catch (IOException e){
			System.err.println("could not find file at " + path);
		}
		
		create();
	}
	
	public void create() {
		width = texture.getWidth();
		height = texture.getHeight();
		textureID = texture.getTextureID();
	}
	
	public void destroy() {
		GL13.glDeleteTextures(textureID);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public int getTextureID() {
		return textureID;
	}
}
