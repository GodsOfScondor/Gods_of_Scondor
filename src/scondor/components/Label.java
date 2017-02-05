package scondor.components;

import scondor.font.Text;

public class Label extends Component {
	
	private Text text;
	
	public Label(String text, int x, int y, float size, int priority) {
		super(x, y, 1, 1);
		this.text = new Text(text, x, y, size, priority);
	}
	
	public void setColor(float r, float g, float b) {
		text.setColor(r, g, b);
	}
	
	public void setText(String text) {
		this.text.setText(text);
		this.text.recreate();
	}
	
	public String getText() {
		return text.getText();
	}
	
	@Override
	protected void discard() {
		text.setTransparency(0f);
	}

	@Override
	protected void showup() {
		text.setTransparency(1f);
	}

	@Override
	protected void destroyComp() {
		text.destroy();
	}

	@Override
	protected void update() {}
	
}
