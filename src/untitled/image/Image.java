package untitled.image;

public class Image {
	
	private int x,y,width,height;
	private Texture tex;
	
	public Image(Texture tex, int x, int y, int width, int height) {
		this.tex = tex;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Texture getTexture() {
		return tex;
	}
	
}
