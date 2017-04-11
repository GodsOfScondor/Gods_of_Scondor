package scondor.components;

import scondor.image.Image;
import scondor.image.Images;
import scondor.inputs.Mouse;

public class CheckBox extends IconButton {
	
	private Image inside;
	private boolean checked;
	private float start_transparency;
	private int padding;
	
	public CheckBox(int x, int y, float size, boolean depending) {
		super(Images.COLOR_BLACK, x, y, (int)(30*size), (int)(30*size), null, depending);
		padding = (int) (size*2);
		inside = new Image(Images.COLOR_GREEN, x+padding,y+padding, width-(2*padding), height-(2*padding), -1);
		super.img.setLayer(0.49f);
		inside.setLayer(0.48f);
		start_transparency = 0.2f;
		inside.setTransparency(start_transparency);
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	@Override
	protected void update() {
		super.update();
		
		if (super.isMouseOver()) {
			if (Mouse.isButtonTyped(0)) {
				checked = !checked;
			}
		}
		
		inside.setTransparency(checked?1f:start_transparency);
		
	}
	
	@Override
	protected void fade(float visibility) {
		if (super.isDepending()) {
			inside.setTransparency(Math.min(start_transparency, visibility));
			
		}
		super.fade(visibility);
	}
	
	@Override
	public void setCompX(int x) {
		inside.setX(padding+x);
		super.setCompX(x);
	}
	
	@Override
	public void setCompY(int y) {
		inside.setY(padding+y);
		super.setCompY(y);
	}
	
	@Override
	protected void validate(int priority) {
		super.validate(priority);
		inside.validate(priority);
	}

}
