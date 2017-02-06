package scondor.image;

import scondor.render.RenderMaster;

public class Image {

	private int x, y, width, height;
	private Texture tex;
	private float transparency;
	private int priority = -1;

	public Image(Texture tex, int x, int y, int width, int height, int priority) {
		this.tex = tex;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.transparency = 1;
		setPriority(priority);
	}

	public void setPriority(int priority) {
		if (this.priority == -1) if (priority != -1) {
			this.priority = priority;
			RenderMaster.addImage(this, priority);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}

	public float getTransparency() {
		return transparency;
	}

	public void setTransparency(float transparency) {
		this.transparency = transparency;
	}

	public void destroy() {
		RenderMaster.removeImage(this, priority);
	}

}
