package scondor.render.font;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import scondor.font.Text;
import scondor.font.TextMaster;
import scondor.render.Render;
import scondor.util.Maths;

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
	
	public void render(List<Text> list) {
		prepare();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		
		for (Text text : list) {
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, TextMaster.getFont(text.getFontID()).getTextureAtlas());
			GL30.glBindVertexArray(text.getMesh());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			shader.loadColor(text.getRedColor(), text.getGreenColor(), text.getBlueColor(), text.getTransparency());
			text.effectFont(shader);
			shader.loadTranslation(text.getX(), (int) (text.getY()/Maths.getScreenRatio()));
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
