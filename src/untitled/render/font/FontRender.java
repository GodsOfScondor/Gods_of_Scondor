package untitled.render.font;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import untitled.font.Text;
import untitled.font.TextMaster;
import untitled.render.Render;

public class FontRender implements Render{

	private static FontShader shader;

	@Override
	public void setup() {
		shader = new FontShader();
	}

	private void prepare() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
		shader.start();
	}
	
	@Override
	public void render() {
		prepare();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, TextMaster.getFont().getTextureAtlas());
		for (Text text : TextMaster.getTexts()) {
			GL30.glBindVertexArray(text.getMesh());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			shader.loadColor(text.getRedColor(), text.getGreenColor(), text.getBlueColor());
			shader.loadTranslation(text.getX(), text.getY());
			shader.loadTransparency(text.getTransparency());
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, text.getVertices());
			GL20.glEnableVertexAttribArray(1);
			GL20.glEnableVertexAttribArray(0);
			GL30.glBindVertexArray(0);
		}
		finish();
	}
	
	private static void finish() {
		shader.stop();
		GL11.glDisable(GL11.GL_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	@Override
	public void close() {
		
	}

}
