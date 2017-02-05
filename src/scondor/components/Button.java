package scondor.components;

import org.lwjgl.opengl.Display;

import scondor.font.FontEffect;
import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.util.Action;
import scondor.util.Maths;

public class Button extends Component {

	private boolean enabled;
	private Action action;
	private Image background;
	private Text text;
	private float r, g, b, size;

	public Button(String text, int x, int y, float size, int priority, Action action) {
		super(x, y, 0, 0);
		enabled = true;
		this.action = action;
		this.enabled = true;
		this.text = new Text(text, x, y, size, priority);
		super.width = (int) (this.text.getWidth());
		super.height = (int) (size*(Display.getHeight() / 32)-10);
		this.r = 0;
		this.g = 0;
		this.b = 0;
		this.size = size;
	}

	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		text.setColor(r, g, b);
	}

	public void setBackground(Texture tex, int priority) {
		if (background != null) {
			background.destroy();
		}
		this.background = new Image(tex, (int)(x-10*size-5), (int)(y-10*size), (int)(width+20*size), (int)((height+20*size)/Maths.getScreenRatio()), priority);
//		this.background = new Image(tex, x, y, width, (int) (height*0.5f), priority);
	}
	
	public void setEffect(FontEffect effect) {
		this.text.setEffect(effect);
	}

	@Override
	protected void discard() {
		if (background != null)
			background.setTransparency(0f);
		if (text != null)
			text.setTransparency(0f);
		enabled = false;
	}

	@Override
	protected void showup() {
		if (background != null)
			background.setTransparency(1f);
		if (text != null)
			text.setTransparency(1f);
		enabled = true;
	}

	@Override
	protected void destroyComp() {
		if (background != null)
			background.destroy();
		if (text != null)
			text.destroy();
	}

	@Override
	protected void update() {
		if (enabled && Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + height) {
			text.setColor(r+0.5f, g+0.5f, b+0.5f);
			if (Mouse.isButtonTyped(0)) {
				if (action != null)
					action.perform();
			}
		} else {
			text.setColor(r, g, b);
		}
	}

}
