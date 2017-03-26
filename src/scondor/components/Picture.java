package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.panels.EffectAble;
import scondor.util.Maths;

public class Picture extends Component implements EffectAble<Picture> {
	
	private Image img;
	
	public Picture(Texture tex, int x, int y, int width, int height) {
		super(x, y, width, height);
		img = new Image(tex, x, y, width, height, -1);
		img.setLayer(0.49f);
	}

	@Override
	protected void discard() {
		if (img!=null) img.setTransparency(0f);
	}

	@Override
	protected void showup() {
		if (img!=null) img.setTransparency(1f);
	}

	@Override
	protected void destroyComp() {
		if (img!=null) img.destroy();
	}

	@Override
	protected void refresh() {
		
	}

	@Override
	protected void setPriority(int priority) {
		if (img!=null) img.setPriority(priority);
	}
	
	public void setX(int x) {
		this.img.setX(x);
	}
	
	public void setY(int y) {
		this.img.setY(y);
	}
	
	public void setWidth(int width) {
		this.img.setWidth(width);
	}
	
	public void setHeight(int height) {
		this.img.setHeight(height);
	}
	
	public boolean isMouseOver() {
		return (Mouse.X >= img.getX() && Mouse.X <= img.getX()+img.getWidth() && Mouse.Y >= img.getY() && Mouse.Y <= img.getY() + img.getHeight() * Maths.getScreenRatio());
	}
	
	@Override
	public Picture fade(float start, float end, int time) {
		img.fade(start, end, time);
		return this;
	}
	
	@Override
	public Picture slideX(int start, int end, int time) {
		img.slideX(start, end, time);
		return this;
	}
	
	@Override
	public Picture slideY(int start, int end, int time) {
		img.slideY(start, end, time);
		return this;
	}
	
	@Override
	public Picture stop() {
		img.stopEffects();
		img.resetEffects();
		return this;
	}

	public void setLayer(float layer) {
		this.img.setLayer(layer);
	}
	
}
