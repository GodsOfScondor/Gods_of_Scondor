package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.util.Maths;

public class Picture extends Component {
	
	protected Image img;
	
	/*
	 * full screen picture
	 */
	public Picture(Texture tex, boolean depending) {
		super(0, 0, 1000, 1 + (int) (1000/Maths.getScreenRatio()), depending);
		img = new Image(tex, super.getCompX(), super.getCompY(), super.getCompWidth(), super.getCompHeight(), -1);
		img.setTransparency(0f);
	}
	
	/*
	 * picture in a rectangle
	 */
	public Picture(Texture tex, int x, int y, int width, int height, boolean depending) {
		super(x, y, width, height, depending);
		img = new Image(tex, x, y, width, height, -1);
		img.setLayer(0.499f);
		img.setTransparency(0f);
	}

	@Override
	public void fade(float start, float end, int duration) {
		img.fade(start, end, duration);
	}

	@Override
	protected void destroy() {
		img.destroy();
	}

	@Override
	protected void fade(float visibility) {
		img.setTransparency(visibility);
	}

	@Override
	protected void validate(int priority) {
		img.validate(priority);
	}

	@Override
	protected void update() {}
	
	@Override
	public void setCompX(int x) {
		img.setX(x);
		super.setCompX(x);
	}
	
	@Override
	public void setCompY(int y) {
		img.setY(y);
		super.setCompY(y);
	}
	
	@Override
	public void setCompWidth(int width) {
		img.setWidth(width);
		super.setCompWidth(width);
	}
	
	@Override
	public void setCompHeight(int height) {
		img.setHeight(height);
		super.setCompHeight(height);
	}
	
}
