package scondor.font;

import scondor.font.effect.FontEffect;
import scondor.font.mesh.TextMeshCreator;
import scondor.render.RenderMaster;
import scondor.render.font.FontShader;
import scondor.util.Slide;

public class Text implements Comparable<Text> {

	private String text;
	private String old = "";
	private float size;

	private int mesh;
	private int vertices;
	private float r=0,g=0,b=0;
	private float line_size;
	private int lines;
	private int x,y;
	private float transparency;
	private float layer;
	private int vbo1;
	private int vbo2;
	private int font_id;
	
	private FontEffect effect;
	private int priority = -1;
	
	private Slide slide_transparency;
	private int s_transparency;
	
	private float b_transparency;
	private int b_x,b_y;
	
	public Text(String text, int x, int y, float size, int font_id, int priority) {
		this.text = text;
		this.size = size;
		this.x = x;
		this.y = y;
		this.line_size = 1;
		this.transparency = 1;
		this.font_id = font_id;
		this.layer = 0.5f;
		validate(priority);
	}
	
	public Text(String text, int x, int y, float size, FontEffect effect, int priority) {
		this.text = text;
		this.size = size;
		this.x = x;
		this.y = y;
		this.line_size = 1;
		this.transparency = 1;
		this.effect = effect;
		validate(priority);
	}
	
	public void validate(int priority) {
		if (this.priority == -1) if (priority != -1) {
			this.priority = priority;
			RenderMaster.addText(this, priority);
		}
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setLineSize(int size) {
		this.line_size = size/1000f;
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

	public float getSize() {
		return size;
	}

	public void setLines(int number) {
		this.lines = number;
	}
	
	/**
	 * 
	 * change current font effect
	 * 
	 */
	public void setEffect(FontEffect effect) {
		this.effect = effect;
	}
	
	/**
	 * 
	 * uploads font effect data to fragment shader
	 * 
	 */
	public void effectFont(FontShader shader) {
		if (effect!= null) effect.loadToShader(shader);
		else {
			shader.loadEffectData(0.5f, 0.1f, 0.4f, 0.1f, 0, 0);
		}
	}
	
	/**
	 * 
	 * @return current active effect
	 * 
	 */
	public FontEffect getEffect() {
		return effect;
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
	
	/**
	 * 
	 * recreates all meshes of the new text and destroys the old ones
	 * 
	 * - works only if text has changed
	 * 
	 */
	public void recreate() {
		if (old.equalsIgnoreCase(text)) return;
		old = text;
		TextMaster.removeText(this);
		TextMaster.addText(this);
	}
	
	public void setSize(float size) {
		this.size = size;
		TextMaster.removeText(this);
		TextMaster.addText(this);
	}

	public float getTransparency() {
		return transparency;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int getWidth() {
		float width = 0;
		for (Line line : TextMaster.getFont(font_id).getBuilder().createStructure(this)) {
			for (Word word : line.getWords()) {
				for (Character c : word.getCharacters()) {
					width+=c.getxAdvance()*size;
				}
				
				width += TextMaster.getFont(font_id).getBuilder().SPACE_WIDTH;
			}
		}
		width *= 1000;
		return (int) (width);
	}
	
	public int getHeight() {
		return (int) (size*TextMeshCreator.LINE_HEIGHT*1000);
	}

	public int getFontID() {
		return font_id;
	}
	
	public float getLayer() {
		return layer;
	}
	
	public void setLayer(float layer) {
		this.layer = layer;
	}
	
	/**
	 * 
	 * @param start - start transparency
	 * @param end - end transparency
	 * @param time - frames to fade
	 * 
	 */
	public void fade(float start, float end, int time) {
		b_transparency = transparency;
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
		transparency = b_transparency;
		x = b_x;
		y = b_y;
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
	
	/*
	 * compares images for layer system
	 */
	@Override
	public int compareTo(Text text) {
		return Float.compare(this.layer, text.getLayer());
	}

}
