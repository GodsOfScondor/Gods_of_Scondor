package scondor.font.effect;

import scondor.render.font.FontShader;

public class ShadowEffect extends FontEffect {

	private float offset;

	public ShadowEffect(float r, float g, float b, float offset) {
		super(r,g,b);
		this.offset = offset;
	}
	
	public ShadowEffect(float offset) {
		super(0,0,0);
		this.offset = offset;
	}
	
	@Override
	public void loadToShader(FontShader shader) {
		shader.loadShadowEffect(r, g, b, offset);
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}

}
