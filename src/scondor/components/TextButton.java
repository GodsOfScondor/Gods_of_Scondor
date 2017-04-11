package scondor.components;

import scondor.font.effect.FontEffect;
import scondor.inputs.Mouse;
import scondor.util.Action;
import scondor.util.Color;

public class TextButton extends Label {

	private Action action;
	private Color color_damper;
	private Color color;
	private boolean over, pressed;
	private float damper;
	
	public TextButton(String text, int x, int y, float size, int font, Action action, boolean depending) {
		super(text, x, y, size, font, depending);
		this.color = new Color(0, 0, 0);
		this.setDamper(0.8f);
		this.action = action;
	}
	
	public TextButton setDamper(float damper) {
		this.damper = damper;
		this.color_damper = new Color(color.r+damper, color.g+damper, color.b+damper);
		return this;
	}
	
	public TextButton setEffect(FontEffect effect) {
		super.setEffect(effect);
		return this;
	}
	
	@Override
	public TextButton setColor(Color color) {
		this.color = color;
		this.setDamper(damper);
		super.setColor(color);
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
		over = (Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + height);
		pressed = (over && Mouse.isButtonPressed(0));
		
		if (over) {
			if (Mouse.isButtonTyped(0)) {
				if (action!=null) action.perform();
			}
			super.setColor(color_damper);
		} else super.setColor(color);
		
	}
	
}
