package scondor.components;

import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.util.Action;
import scondor.util.Maths;

public class IconButton extends Picture {
	
	private Action action;
	private float damper = 0.3f, visibility;
	private float resize = 1f;
	private int r_x,r_y,r_width,r_height;
	private boolean over, pressed, before;
	
	public IconButton(Texture tex, int x, int y, int width, int height, Action action, boolean depending) {
		super(tex, x, y, width, height, depending);
		this.visibility = 1;
		this.action = action;
		r_x = x;
		r_y = y;
		r_width = width;
		r_height = height;
	}
	
	public IconButton setDamper(float damper) {
		this.damper = damper;
		return this;
	}
	
	public IconButton setResize(float resize) {
		this.resize = resize;
		return this;
	}
	
	public boolean isMouseOver() {
		return over;
	}
	
	public boolean isButtonPressed() {
		return pressed;
	}
	
	@Override
	protected void update() {
		
		before = !over;
		
		over = (Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + height*Maths.getScreenRatio());
		pressed = (over && Mouse.isButtonPressed(0));
		
		if (over) {
			if (Mouse.isButtonTyped(0)) {
				if (action!=null) action.perform();
			}
			if (before) {
				super.fade(visibility-damper);
				super.setCompWidth((int) (super.getCompWidth()*resize));
				super.setCompHeight((int) (super.getCompHeight()*resize));
				super.setCompX(r_x - (super.getCompWidth() - r_width)/2);
				super.setCompY(r_y - (super.getCompHeight() - r_height)/2);
			}
		} else {
			super.fade(visibility);
			super.setCompWidth(r_width);
			super.setCompHeight(r_height);
			super.setCompX(r_x);
			super.setCompY(r_y);
		}
		
	}
	
}
