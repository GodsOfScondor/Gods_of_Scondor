package scondor.font;

import scondor.render.font.FontShader;

public class GlowEffect extends FontEffect {
	
	private float glow;
	
	public GlowEffect(float r, float g, float b, float glow) {
		super(r,g,b);
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
