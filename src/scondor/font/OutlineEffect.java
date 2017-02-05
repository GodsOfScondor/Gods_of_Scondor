package scondor.font;

import scondor.render.font.FontShader;
import scondor.util.Color;

public class OutlineEffect extends FontEffect {

	private float r,g,b;
	private float outline;

	public OutlineEffect(Color color, float outline) {
		super(color);
		this.outline = outline;
	}

	@Override
	public void loadToShader(FontShader shader) {
		shader.loadOutlineEffect(r,g,b,outline);
	}

}
