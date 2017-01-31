package scondor.render.font;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import scondor.render.ShaderProgram;

public class FontShader extends ShaderProgram {
	
	private Vector2f translation = new Vector2f();
	private Vector2f offset = new Vector2f();
	private Vector3f color = new Vector3f();
	
	private int loc_color;
	private int loc_translation;
	private int loc_transparency;
	
	private int loc_width;
	private int loc_edge;
	private int loc_border_width;
	private int loc_border_edge;
	private int loc_outline_color;
	private int loc_offset;
	
	public FontShader() {
		super("font/Font.vsh", "font/Font.fsh");
	}

	@Override
	protected void getAllUniformLocations() {
		loc_color = super.getUniformLocation("color");
		loc_translation = super.getUniformLocation("translation");
		loc_transparency = super.getUniformLocation("transparency");
		
		loc_width = super.getUniformLocation("width");
		loc_edge = super.getUniformLocation("edge");
		loc_border_width = super.getUniformLocation("border_width");
		loc_border_edge = super.getUniformLocation("border_edge");
		loc_outline_color = super.getUniformLocation("outline_color");
		loc_offset = super.getUniformLocation("offset");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "tex_coords");
	}
	
	public void loadColor(float r, float g, float b, float transparency) {
		color.x = r;
		color.y = g;
		color.z = b;
		super.loadFloat(loc_transparency, transparency);
		super.loadVector3f(loc_color, color);
	}
	
	public void loadGlowEffect(float r, float g, float b, float glow) {
		color.x = r;
		color.y = g;
		color.z = b;
		super.loadVector3f(loc_outline_color, color);
		offset.x = 0;
		offset.y = 0;
		super.loadFloat(loc_width, 0.5f);
		super.loadFloat(loc_edge, 0.1f);
		super.loadFloat(loc_border_width, 0.15f-glow*0.4f);
		super.loadFloat(loc_border_edge, 0.5f+glow*0.52f);
		super.loadVector2f(loc_offset, offset);
	}
	
	public void loadShadowEffect(float r, float g, float b, float o) {
		color.x = r;
		color.y = g;
		color.z = b;
		super.loadVector3f(loc_outline_color, color);
		offset.x = 0.001f*o;
		offset.y = -0.001f*o;
		super.loadFloat(loc_width, 0.5f);
		super.loadFloat(loc_edge, 0.1f);
		super.loadFloat(loc_border_width, 0.5f);
		super.loadFloat(loc_border_edge, 0.1f);
		super.loadVector2f(loc_offset, offset);
	}
	
	public void loadTranslation(int x, int y) {
		translation.x = (2*x)/1000f;
		translation.y = (-2*y)/(1000f*Display.getHeight()/Display.getWidth());
		super.loadVector2f(loc_translation, translation);
	}
	
}
