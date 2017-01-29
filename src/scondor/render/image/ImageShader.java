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
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}
	
	public void loadTM(Matrix4f TM) {
		super.loadMatrix(loc_TM, TM);
	}
	
	public void loadData(Texture texture) {
		offset.setX(texture.getTexX()/texture.getColumns());
		offset.setY(texture.getTexY()/texture.getRows());
		super.loadVector2f(loc_offset, offset);
		super.loadFloat(loc_columns, texture.getColumns());
		super.loadFloat(loc_rows, texture.getRows());
	}
	
}
