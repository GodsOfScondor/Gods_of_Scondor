package scondor.components;

import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.util.Action;
import scondor.util.Maths;

public class IconButton extends Picture {
	
	private Action action;
	
	public IconButton(Texture tex, int x, int y, int width, int height, Action action, boolean depending) {
		super(tex, x, y, width, height, depending);
		this.action = action;
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
		super.update();
		if (Mouse.isButtonTyped(0) && super.isMouseOver() && action != null) action.perform();
	}
	
}
