package scondor.image;

import scondor.render.RenderMaster;
import scondor.util.Slide;

public class Image implements Comparable<Image>{

	private int x, y, width, height;
	private Texture tex;
	private float transparency;
	private float layer;
	private int priority = -1;
	
	private Slide slide_transparency;
	private int s_transparency;
	private float b_tranyparency;

	public Image(Texture tex, int x, int y, int width, int height, int priority) {
		this.tex = tex;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.transparency = 1;
		layer = 0.5f;
		validate(priority);
	}

	public void validate(int priority) {
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
		this.resetEffects();
		this.transparency = transparency;
	}
	
	public void setTexture(Texture tex) {
		this.tex = tex;
	}

	public void destroy() {
		RenderMaster.removeImage(this, priority);
	}
	
	/**
	 * 
	 * @param start - start transparency
	 * @param end - end transparency
	 * @param time - frames to fade
	 * 
	 */
	public void fade(float start, float end, int time) {
		
		if (transparency<0.001 && end < 0.001) return;
		
		b_tranyparency = transparency;
		slide_transparency = new Slide((int)(1000*start), (int)(1000*end), time);
		transparency = start;
		slide_transparency.run();
		s_transparency=0;
	}
	
	/**
	 * 
	 * stops effects
	 * 
	 */
	public void stopEffects() {
		slide_transparency = null;
		s_transparency = 0;
	}
	
	/**
	 * 
	 * resets position and transparency
	 * 
	 */
	public void resetEffects() {
		transparency = b_tranyparency;
	}
	
	/**
	 * 
	 * updates slide effects
	 * 
	 */
	public void update() {
		/*
		 * fade transparency
		 */
		if (slide_transparency!=null) {
			transparency = (slide_transparency.getValue()/1000f);
			s_transparency++;
			if (s_transparency>=slide_transparency.getTime()) {
				transparency = (slide_transparency.getEndValue()/1000f);
				slide_transparency.destroy();
				slide_transparency=null;
				s_transparency=0;
			}
		}
	}
	
	public float getLayer() {
		return layer;
	}
	
	public void setLayer(float layer) {
		this.layer = layer;
	}
	
	/*
	 * compares images for layer system
	 */
	@Override
	public int compareTo(Image img) {
		return Float.compare(img.getLayer(), this.layer);
	}

	public boolean isFading() {
		return slide_transparency!=null;
	}

}
