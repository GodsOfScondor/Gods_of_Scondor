package scondor.font;

import scondor.render.font.FontShader;
import scondor.util.Color;

public abstract class FontEffect {
	
	protected float r,g,b;

	public FontEffect(Color color) {
		r = 1f/color.getR();
		g = 1f/color.getG();
		b = 1f/color.getB();
	}

	public Color getColor() {
		return new Color(((int)r*256), ((int)g*256), ((int)b*256));
	}
	
	public abstract void loadToShader(FontShader shader);
	
}
