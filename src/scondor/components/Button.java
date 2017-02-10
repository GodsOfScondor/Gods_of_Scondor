package scondor.components;

import org.lwjgl.opengl.Display;

import scondor.font.Text;
import scondor.font.effect.FontEffect;
import scondor.inputs.Mouse;
import scondor.util.Action;

public class Button extends Component {

	private boolean enabled;
	private Action action;
	private Text text;
	private float r, g, b;
	private float damper;

	public Button(String text, int x, int y, float size, int font_id, Action action) {
		super(x, y, 0, 0);
		enabled = true;
		this.action = action;
		this.enabled = true;
		this.text = new Text(text, x, y, size, font_id, -1);
		super.width = 1;
		super.height = (int) (size*(Display.getHeight() / 32)-10);
		this.r = 0;
		this.g = 0;
		this.b = 0;
		this.damper = 0.5f;
	}

	public Button setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		text.setColor(r, g, b);
		return this;
	}
	
	public Button setDamper(float damper) {
		this.damper = damper;
		return this;
	}
	
	public Button setEffect(FontEffect effect) {
		this.text.setEffect(effect);
		return this;
	}

	@Override
	protected void discard() {
		if (text != null)
			text.setTransparency(0f);
		enabled = false;
	}

	@Override
	protected void showup() {
		if (text != null)
			text.setTransparency(1f);
		enabled = true;
	}

	@Override
	protected void destroyComp() {
		if (text != null)
			text.destroy();
	}

	@Override
	protected void update() {
		if (enabled && Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + height) {
			text.setColor(r+damper, g+damper, b+damper);
			if (Mouse.isButtonTyped(0)) {
				if (action != null)
					action.perform();
			}
		} else {
			text.setColor(r, g, b);
		}
	}

	@Override
	protected void setPriority(int priority) {
		text.setPriority(priority);
		super.width = (int) (this.text.getWidth());
	}
	
	public Button fade(float start, float end, int time) {
		text.fade(start, end, time);
		return this;
	}
	
	public Button slideX(int start, int end, int time) {
		text.slideX(start, end, time);
		return this;
	}
	
	public Button slideY(int start, int end, int time) {
		text.slideY(start, end, time);
		return this;
	}

}
