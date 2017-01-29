package scondor.font;

import org.lwjgl.opengl.Display;

public class Text {

	private String text;
	private String old = "";
	private float size;

	private int mesh;
	private int vertices;
	private float r=0,g=0,b=0;
	private float line_size;
	private int lines;
	private boolean center = false;
	private int x,y;
	private float transparency;
	private int mode;
	private int vbo1;
	private int vbo2;
	
	public Text(String text, int x, int y, float size, int mode) {
		this.text = text;
		this.size = size;
		this.x = x;
		this.y = y;
		this.line_size = 1;
		this.transparency = 1;
		this.mode = mode;
		TextMaster.addText(this, mode);
	}

	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}

	public float getRedColor() {
		return r;
	}
	
	public float getGreenColor() {
		return g;
	}
	
	public float getBlueColor() {
		return b;
	}

	public int getLines() {
		return lines;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLineSize(int size) {
		this.line_size = size/1000f;
	}
	
	public void setSize(float size) {
		this.size = size;
	}

	public int getMesh() {
		return mesh;
	}
	
	public int getVBO1() {
		return vbo1;
	}
	
	public int getVBO2() {
		return vbo2;
	}

	public void setMeshInfo(int vao_id, int verticesCount) {
		this.mesh = vao_id;
		this.vertices = verticesCount;
	}

	public int getVertices() {
		return this.vertices;
	}

	protected float getSize() {
		return size;
	}

	protected void setLines(int number) {
		this.lines = number;
	}

	protected boolean isCentered() {
		return center;
	}

	public String getText() {
		return text;
	}

	public float getMaxLineSize() {
		return line_size;
	}

	public void destroy() {
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void recreate() {
		if (old.equalsIgnoreCase(text)) return;
		old = text;
		TextMaster.removeText(this, mode);
		TextMaster.addText(this, mode);
	}

	public float getTransparency() {
		return transparency;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int getWidth() {
		int width = 0;
		for (Line line : TextMaster.getFont().getBuilder().createStructure(this)) {
			for (Word word : line.getWords()) {
				for (Character c : word.getCharacters()) {
					width+=c.getxAdvance()*Display.getWidth()*size;
				}
			}
		}
		return width;
	}

}
