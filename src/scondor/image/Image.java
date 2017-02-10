package scondor.image;

import scondor.render.RenderMaster;
import scondor.util.Slide;

public class Image {

	private int x, y, width, height;
	private Texture tex;
	private float transparency;
	private int priority = -1;
	
	private Slide slide_x;
	private Slide slide_y;
	private Slide slide_transparency;
	private int s_x,s_y,s_tranyparency;

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
	
	/**
	 * 
	 * @param start - start transparency
	 * @param end - end transparency
	 * @param time - frames to fade
	 * 
	 */
	public void fade(float start, float end, int time) {
		slide_transparency = new Slide((int)(1000*start), (int)(1000*end), time);
		transparency = start;
		slide_transparency.run();
		s_tranyparency=0;
	}
	
	/**
	 * 
	 * @param start - start x
	 * @param end - end x
	 * @param time - frames to slide
	 * 
	 */
	public void slideX(int start, int end, int time) {
		slide_x = new Slide(start, end, time);
		x = start;
		slide_x.run();
		s_x=0;
	}
	
	/**
	 * 
	 * @param start - start y
	 * @param end - end y
	 * @param time - frames to slide
	 * 
	 */
	public void slideY(int start, int end, int time) {
		slide_y = new Slide(start, end, time);
		y = start;
		slide_y.run();
		s_y=0;
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
			s_tranyparency++;
			if (s_tranyparency>=slide_transparency.getTime()) {
				transparency = (slide_transparency.getEndValue()/1000f);
				slide_transparency.destroy();
				slide_transparency=null;
				s_tranyparency=0;
			}
		}
		
		/*
		 * slide x
		 */
		if (slide_x!=null) {
			x = slide_x.getValue();
			s_x++;
			if (s_x>=slide_x.getTime()) {
				x = slide_x.getEndValue();
				slide_x.destroy();
				slide_x=null;
				s_x=0;
			}
		}
		
		/*
		 * slide y
		 */
		if (slide_y!=null) {
			y = slide_y.getValue();
			s_y++;
			if (s_y>=slide_y.getTime()) {
				y = slide_y.getEndValue();
				slide_y.destroy();
				slide_y=null;
				s_y=0;
			}
		}
	}

}
