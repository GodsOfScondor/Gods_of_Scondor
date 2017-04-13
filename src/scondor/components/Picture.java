package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.util.Maths;

public class Picture extends Component {
	
	protected Image img;
	protected float damper = 0f, visibility;
	protected float resize = 1f;
	protected boolean over, before, pressed;
	protected int r_x,r_y,r_width,r_height;
	
	/*
	 * full screen picture
	 */
	public Picture(Texture tex, boolean depending) {
		super(0, 0, 1000, 1 + (int) (1000/Maths.getScreenRatio()), depending);
		img = new Image(tex, super.getCompX(), super.getCompY(), super.getCompWidth(), super.getCompHeight(), -1);
		img.setTransparency(0f);
		r_x = x;
		r_y = y;
		r_width = width;
		r_height = height;
		visibility = 1f;
	}
	
	/*
	 * picture in a rectangle
	 */
	public Picture(Texture tex, int x, int y, int width, int height, boolean depending) {
		super(x, y, width, height, depending);
		img = new Image(tex, x, y, width, height, -1);
		img.setLayer(0.499f);
		img.setTransparency(0f);
		r_x = x;
		r_y = y;
		r_width = width;
		r_height = height;
		visibility = 1f;
	}
	
	public Picture setLayer(float layer) {
		img.setLayer(layer);
		return this;
	}
	
	public Picture setResize(float resize) {
		this.resize = resize;
		return this;
	}
	
	public boolean isMouseOver() {
		return over;
	}

	@Override
	public void fade(float start, float end, int duration) {
		visibility=end;
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
	protected void update() {
		
		before = !over;
		
		over = (Mouse.X >= img.getX() && Mouse.X <= img.getX() + width && Mouse.Y >= img.getY() && Mouse.Y <= img.getY() + height*Maths.getScreenRatio());
		pressed = (over && Mouse.isButtonPressed(0));
		
		if (over) {
			if (before) {
				fade(visibility-damper);
				setCompWidth((int) (super.getCompWidth()*resize));
				setCompHeight((int) (super.getCompHeight()*resize));
				img.setX(r_x - (super.getCompWidth() - r_width)/2);
				img.setY(r_y - (super.getCompHeight() - r_height)/2);
			}
		} else {
			fade(visibility);
			setCompWidth(r_width);
			setCompHeight(r_height);
			img.setX(r_x);
			img.setY(r_y);
		}
		
	}
	
	@Override
	public void setCompX(int x) {
		r_x = x;
		img.setX(x);
		super.setCompX(x);
	}
	
	@Override
	public void setCompY(int y) {
		r_y = y;
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
