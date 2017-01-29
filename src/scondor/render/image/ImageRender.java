package scondor.render.image;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import scondor.Loader;
import scondor.image.Image;
import scondor.render.Render;
import scondor.util.Maths;

public class ImageRender implements Render {
	
	private static int vao_id,vc;
	private static ImageShader shader;
	
	@Override
	public void setup() {
		int[] data = Loader.loadToVAO(new float[] { -1, 1, -1, -1, 1, 1, 1, -1 }, 2);
		vao_id = data[0];
		vc = data[1];
		shader = new ImageShader();
	}

	public void render(List<Image> list) {
		GL30.glBindVertexArray(vao_id);
		GL20.glEnableVertexAttribArray(0);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		shader.start();
		for (Image img : list) {
			
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, img.getTexture().getID());
			
			shader.loadTM(Maths.createTM(img.getX(), img.getY(), img.getWidth(), img.getHeight()));
			shader.loadData(img.getTexture());
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, vc);
		}
		shader.stop();
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void close() {
		
	}
	
}
