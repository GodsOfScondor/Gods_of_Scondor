package scondor.font;

import scondor.render.font.FontShader;

public abstract class FontEffect {
	
	protected float r,g,b;

	public FontEffect(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public abstract void loadToShader(FontShader shader);
	
}
