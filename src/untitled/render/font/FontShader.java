package untitled.render.font;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import untitled.render.ShaderProgram;

public class FontShader extends ShaderProgram {
	
	private Vector2f translation = new Vector2f();
	private Vector3f color = new Vector3f();
	
	private int loc_color;
	private int loc_translation;
	private int loc_transparency;

	public FontShader() {
		super("font/Font.vsh", "font/Font.fsh");
	}

	@Override
	protected void getAllUniformLocations() {
		loc_color = super.getUniformLocation("color");
		loc_translation = super.getUniformLocation("translation");
		loc_transparency = super.getUniformLocation("transparency");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "tex_coords");
	}

	public void loadTransparency(float transparency) {
		super.loadFloat(loc_transparency, transparency);
	}
	
	public void loadColor(float r, float g, float b) {
		color.x = r;
		color.y = g;
		color.z = b;
		super.loadVector3f(loc_color, color);
	}
	
	public void loadTranslation(int x, int y) {
		translation.x = (2*x)/1000f;
		translation.y = (-2*y)/(1000f*Display.getHeight()/Display.getWidth());
		super.loadVector2f(loc_translation, translation);
	}
	
}
