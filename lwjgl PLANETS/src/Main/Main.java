package Main;

import org.lwjgl.glfw.GLFW;

import engine.Math.Vector.*;
import engine.UTILS.FileUtils;
import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.io.Input;
import engine.io.Window;
import engine.objects.GameObject;

public class Main implements Runnable {
	public Shader shader;
	public Renderer renderer;
	public Mesh mesh;
	public Thread game;
	public Window window;
	public final int WIDTH = 1280, HEIGHT = 760;
	public GameObject object;
	
	public void start() {	
		game = new Thread(this, "game");
		game.start();
	}
	
	public void init() {
		window = new Window(WIDTH, HEIGHT, "Game");
		window.setBackgroundColor(0.05f, 0.045f, 0.06f);
		
		shader = new Shader("/shaders/MainVertex.glsl", "/shaders/MainFrag.glsl");
		renderer = new Renderer(shader);
		load();
		
		window.create();
		
		mesh = Mesh.getRectMesh();
		
		mesh.create();
		
		object = new GameObject(mesh, new Vector3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(1, 1, 1));
		
		shader.create();
	}
	
	public void run() {
		//Am I real??????? (depressed jesus)
		
		init();
		while (!window.shouldClose()) {
			update();
			render();
			if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) window.setFullscreen(!window.isFullscreen());;
		}
		close();
	}
	
	private void close() {
		save();
		
		mesh.destroy();
		window.destroy();
		shader.destroy();
	}
	
	private void save() {
		
	}
	
	private void load() {
		
	}
	
	private solSystem loadSystem(int index) {
		return new solSystem(FileUtils.loadAsString("saves/systems" + index));
	}
	private void update() {
		window.update();
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) System.out.println("X: " + Input.getMouseX() + ", Y: " + Input.getMouseY());
	}
	
	private void render() {
		renderer.renderObject(object);
		window.swapBuffers();
	}
	
	public static void main(String[] args) {
		new Main().start();
	}
}