package scondor.components;

import scondor.font.Text;
import scondor.font.effect.FontEffect;
import scondor.util.Color;

public class Label extends Component {
	
	private Text text;
	
	public Label(String text, int x, int y, float size, int font, boolean depending) {
		super(x, y, -1, -1, depending);
		this.text = new Text(text, x, y, size, font, -1);
		super.width = this.text.getWidth();
		super.height = this.text.getHeight();
		this.text.setTransparency(0f);
	}

	public Label setColor(Color color) {
		text.setColor(color.r, color.g, color.b);
		return this;
	}
	
	public Label setEffect(FontEffect effect) {
		text.setEffect(effect);
		return this;
	}
	
	public void setText(String text) {
		this.text.setText(text);
		this.text.recreate();
		super.setCompWidth(this.text.getWidth());
	}
	
	public void setSize(float size) {
		this.text.setSize(size);
		this.text.recreate();
	}
	
	public String getText() {
		return text.getText();
	}
	
	public float getSize() {
		return text.getSize();
	}
	
	public Label setLineSize(int size) {
		text.setLineSize(size);
		return this;
	}
	
	@Override
	protected void destroy() {
		text.destroy();
	}

	@Override
	protected void fade(float visibility) {
		text.setTransparency(visibility);
	}

	@Override
	protected void validate(int priority) {
		text.validate(priority);
	}

	@Override
	public void fade(float start, float end, int duration) {
		text.fade(start, end, duration);
	}

	@Override
	protected void update() {}
	
	public void setCompX(int x) {
		text.setX(x);
		super.setCompX(x);
	}
	
	public void setCompY(int y) {
		text.setY(y);
		super.setCompY(y);
	}
	
}
