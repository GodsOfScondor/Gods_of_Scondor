package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;

public class Picture extends Component {
	
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
	
	public Picture fade(float start, float end, int time) {
		img.fade(start, end, time);
		return this;
	}
	
	public Picture slideX(int start, int end, int time) {
		img.slideX(start, end, time);
		return this;
	}
	
	public Picture slideY(int start, int end, int time) {
		img.slideY(start, end, time);
		return this;
	}
	
}
