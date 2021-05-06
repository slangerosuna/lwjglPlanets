package engine.Math.Matrix;

import engine.Math.Vector.*;

public class Matrix4 {
	public static final int SIZE = 4;
	private float[] elements = new float[SIZE * SIZE];
	
	public static Matrix4 transform(Vector3 pos, Vector3 rot, Vector3 scale) {
		Matrix4 result = Matrix4.identity();
		
		Matrix4 translationMatrix = Matrix4.translation(pos);
		Matrix4 scaleMatrix = Matrix4.scale(scale);
		
		Matrix4 rotXMatrix = Matrix4.rotate(rot.x, new Vector3(1, 0, 0));
		Matrix4 rotYMatrix = Matrix4.rotate(rot.y, new Vector3(0, 1, 0));
		Matrix4 rotZMatrix = Matrix4.rotate(rot.z, new Vector3(0, 0, 1));
		
		Matrix4 rotMatrix = Matrix4.Multiply(rotXMatrix, Matrix4.Multiply(rotYMatrix, rotZMatrix));
		
		result = Matrix4.Multiply(scaleMatrix, Matrix4.Multiply(rotMatrix, translationMatrix));
		
		return result;
	}
	
	public static Matrix4 identity() {
		Matrix4 result = new Matrix4();
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < SIZE; y++) {
				result.set(x, y, 0);
			}
		}
		
		result.set(0, 0, 1);
		result.set(1, 1, 1);
		result.set(2, 2, 1);
		result.set(3, 3, 1);
		
		return result;
	}
	
	public static Matrix4 translation(Vector3 translate) {
		Matrix4 result = Matrix4.identity();
		
		result.set(0, 3, translate.x);
		result.set(1, 3, translate.y);
		result.set(2, 3, translate.z);
		
		return result;
	}
	
	public static Matrix4 rotate(float angle, Vector3 axis) {
		Matrix4 result = Matrix4.identity();
		
		float cos = (float) Math.cos(Math.toRadians(angle));
		float sin = (float) Math.sin(Math.toRadians(angle));
		float C = 1 - cos;
		
		result.set(0, 0, cos + axis.x * axis.x * C);
		result.set(0, 1, axis.x * axis.y * C - axis.z * sin);
		result.set(0, 2, axis.x * axis.z * C + axis.y * sin);
		result.set(1, 0, axis.y * axis.x * C + axis.z * sin);
		result.set(1, 1, cos + axis.y * axis.y * C);
		result.set(1, 2, axis.y * axis.z * C - axis.x * sin);
		result.set(2, 0, axis.z * axis.x * C - axis.y * sin);
		result.set(2, 1, axis.z * axis.z * C + axis.x * sin);
		result.set(2, 2, cos + axis.z * axis.x * C);
		
		return result;
	}
	
	public static Matrix4 scale(Vector3 scalar) {
		Matrix4 result = Matrix4.identity();
		
		result.set(0, 0, scalar.x);
		result.set(1, 1, scalar.y);
		result.set(2, 2, scalar.z);
		
		return result;
	}
	
	public static Matrix4 Multiply(Matrix4 a, Matrix4 b) {
		Matrix4 result = Matrix4.identity();
		
		for(int x = 0; x < SIZE; x++) {
			for(int y = 0; y < SIZE; y++) {
				result.set(x, y, a.get(x, 0) * b.get(0, y) +
								 a.get(x, 1) * b.get(1, y) +
								 a.get(x, 2) * b.get(2, y) +
								 a.get(x, 3) * b.get(3, y));
			}
		}
		
		return result;
	}
	
	public float get(int x, int y) {
		return elements[x + (y * SIZE)];
	}
	
	public void set(int x, int y, float value) {
		elements[x + (y * SIZE)] = value;
	}
	
	public float[] getAll(){
		return elements;
	}
}
