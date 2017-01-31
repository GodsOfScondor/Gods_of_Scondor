package scondor.font;

import scondor.render.font.FontShader;
import scondor.util.Color;

public class GlowEffect extends FontEffect {
	
	private float glow;
	
	public GlowEffect(Color color, float glow) {
		super(color);
		this.glow = glow;
	}
	
	public void setGlow(float glow) {
		this.glow = glow;
	}
	
	public float getGlow() {
		return glow;
	}

	@Override
	public void loadToShader(FontShader shader) {
		shader.loadGlowEffect(r, g, b, glow);
	}
	
}
