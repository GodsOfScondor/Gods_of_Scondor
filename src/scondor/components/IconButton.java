package scondor.components;

import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.util.Action;
import scondor.util.Maths;

public class IconButton extends Picture {
	
	private Action action;
	private float damper = 0.3f, visibility;
	private boolean over, pressed;
	
	public IconButton(Texture tex, int x, int y, int width, int height, Action action, boolean depending) {
		super(tex, x, y, width, height, depending);
		this.visibility = 1;
		this.action = action;
	}
	
	public IconButton setDamper(float damper) {
		this.damper = damper;
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
		over = (Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + height*Maths.getScreenRatio());
		pressed = (over && Mouse.isButtonPressed(0));
		
		if (over) {
			if (Mouse.isButtonTyped(0)) {
				if (action!=null) action.perform();
			}
			super.fade(visibility-damper);
		} else super.fade(visibility);
		
	}
	
}
