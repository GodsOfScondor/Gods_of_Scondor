package scondor.font.effect;

import scondor.render.font.FontShader;

public class OutlineEffect extends FontEffect {

	private float outline;

	public OutlineEffect(float r, float g, float b, float outline) {
		super(r,g,b);
		this.outline = outline;
	}

	@Override
	public void loadToShader(FontShader shader) {
		shader.loadOutlineEffect(r,g,b,outline);
	}

}
