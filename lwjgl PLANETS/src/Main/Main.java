package Main;

import org.lwjgl.glfw.GLFW;

import engine.Math.Vector.*;
import engine.graphics.*;
import engine.io.*;

public class Main implements Runnable {
	public Shader shader;
	public Renderer renderer;
	public Mesh mesh;
	public Thread game;
	public Window window;
	public final int WIDTH = 1280, HEIGHT = 760;
	
	public void start() {	
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		window = new Window(WIDTH, HEIGHT, "Game");
		window.setBackgroundColor(0.05f, 0.045f, 0.06f);
		window.create();
		
		shader = new Shader("/shaders/MainVertex.glsl", "/shaders/MainFrag.glsl");
		renderer = new Renderer(shader);
		
		mesh = Mesh.getRectMesh();
		mesh.create();
		shader.create();
	}
	
	public void run() {
		//Am I real??????? (depressed jesus)
		
		init();
		while (!window.shouldClose()) {
			update();
			render();
			if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) return;
		}
		window.destroy();
	}
	
	private void update() {
		window.update();
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
	}
	
	private void render() {
		renderer.renderMesh(mesh);
		window.swapBuffers();
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
}