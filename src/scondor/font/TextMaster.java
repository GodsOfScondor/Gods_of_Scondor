package scondor.font;

import java.io.File;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import scondor.Loader;
import scondor.image.Texture;
import scondor.render.RenderMaster;

public class TextMaster {
	
	private static FontType font;
	private static TextMeshData data;
	
	public static void init() {
		font = new FontType(new Texture("font/field").getID(), new File("res/font/field.fnt"));
		addText(new Text("Hallo", 0, 0, 7), 4);
	}
	
	public static void addText(Text text, int priority) {
		data = font.loadText(text);
		int[] xdata = Loader.loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(xdata[0], xdata[1]);
		RenderMaster.addText(text, priority);
	}
	
	public static void removeText(Text text, int priority) {
		destroy(text);
		text.destroy();
	}
	
	private static void destroy(Text text) {
		// Delete the VBO
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(text.getVBO1());
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 1);
        GL15.glDeleteBuffers(text.getVBO2());
         
        // Delete the VAO
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(text.getMesh());
	}
	
	public static FontType getFont() {
		return font;
	}

}
