package scondor.image;

import scondor.Loader;

public class Texture {
	
	private int id;
	private float columns,rows;
	protected int t_x,t_y;
	
	public Texture(String path) {
		id = Loader.loadTexture(path)[0];
		rows = 1f;
		columns = 1f;
		t_x = 0;
		t_y = 0;
	}
	
	public Texture(String path, float columns, float rows) {
		id = Loader.loadTexture(path)[0];
		this.columns = columns;
		this.rows = rows;
		t_x = 0;
		t_y = 0;
	}
	
	public Texture(Texture texture, int t_x, int t_y) {
		id = texture.getID();
		columns = texture.getColumns();
		rows = texture.getRows();
		this.t_x = t_x;
		this.t_y = t_y;
	}
	
	public int getID() {
		return id;
	}
	
	public float getRows() {
		return rows;
	}
	
	public float getColumns() {
		return columns;
	}
	
	public int getTexX() {
		return t_x;
	}
	
	public int getTexY() {
		return t_y;
	}

}
