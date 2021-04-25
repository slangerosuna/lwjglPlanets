package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

public class Renderer {
	public void renderMesh(Mesh mesh) {
		GL30.glBindVertexArray(mesh.getVAO());
		GL30.glEnableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
		
		/*Jesus is best, and he gives good head
		^ that is to get free vBucks, but unlike Jesus arata67 gives shit head,
		  this guy on Reddit said that that is how it works and why would I not believe him 
		  
		  "And Josiah said upon to the lord, wow that's shit head, and thus the lord hath forsaken Josiah"
		  
		  This is why we must compliment the quality of head given by Jesus for vBucks*/

		
		GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}