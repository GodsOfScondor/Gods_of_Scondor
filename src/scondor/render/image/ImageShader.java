package scondor.render.image;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

import scondor.image.Texture;
import scondor.render.ShaderProgram;

public class ImageShader extends ShaderProgram {

	private int loc_TM;
	private int loc_rows;
	private int loc_columns;
	private int loc_offset;
	private int loc_transparency;
	
	private int loc_layer;
	private int loc_priority;
	
	private Vector2f offset;

	public ImageShader() {
		super("image/Image.vsh", "image/Image.fsh");
		offset = new Vector2f();
	}

	@Override
	protected void getAllUniformLocations() {
		loc_TM = super.getUniformLocation("TM");
		loc_rows = super.getUniformLocation("rows");
		loc_columns = super.getUniformLocation("columns");
		loc_offset = super.getUniformLocation("offset");
		loc_transparency = super.getUniformLocation("transparency");
		
		loc_layer = super.getUniformLocation("layer");
		loc_priority = super.getUniformLocation("priority");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	public void loadTextureData(Texture texture) {
		offset.setX(texture.getTexX()/texture.getColumns());
		offset.setY(texture.getTexY()/texture.getRows());
		super.loadVector2f(loc_offset, offset);
		super.loadFloat(loc_columns, texture.getColumns());
		super.loadFloat(loc_rows, texture.getRows());
	}
	
	public void loadImageData(Matrix4f TM, float transparency, float layer) {
		super.loadFloat(loc_transparency, transparency);
		super.loadFloat(loc_layer, layer);
		super.loadMatrix(loc_TM, TM);
	}
	
	public void loadPriority(int priority) {
		super.loadInt(loc_priority, priority);
	}
	
}
