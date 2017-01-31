package scondor.font;

import scondor.render.font.FontShader;
import scondor.util.Color;

public class ShadowEffect extends FontEffect {

	private float offset;

	public ShadowEffect(Color color, float offset) {
		super(color);
		this.offset = offset;
	}
	
	public ShadowEffect(float offset) {
		super(new Color(0, 0, 0));
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
