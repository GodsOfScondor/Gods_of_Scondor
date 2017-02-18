package scondor.components;

import scondor.font.Text;
import scondor.font.effect.FontEffect;
import scondor.panels.EffectAble;

public class Label extends Component implements EffectAble<Label> {
	
	private Text text;
	
	public Label(String text, int x, int y, float size, int font_id) {
		super(x, y, 1, 1);
		this.text = new Text(text, x, y, size, font_id, -1);
	}
	
	public Label setColor(float r, float g, float b) {
		text.setColor(r, g, b);
		return this;
	}
	
	public Label setText(String text) {
		this.text.setText(text);
		this.text.recreate();
		return this;
	}
	
	public Label setEffect(FontEffect effect) {
		if (text!=null) text.setEffect(effect);
		return this;
	}
	
	@Override
	protected void discard() {
		if (text!=null) {
			text.stopEffects();
			text.setTransparency(0f);
		}
	}

	@Override
	protected void showup() {
		if (text!=null) text.setTransparency(1f);
	}

	@Override
	protected void destroyComp() {
		text.destroy();
	}

	@Override
	protected void refresh() {}

	@Override
	protected void setPriority(int priority) {
		text.setPriority(priority);
	}
	
	@Override
	public Label fade(float start, float end, int time) {
		text.fade(start, end, time);
		return this;
	}
	
	@Override
	public Label slideX(int start, int end, int time) {
		text.slideX(start, end, time);
		return this;
	}
	
	@Override
	public Label slideY(int start, int end, int time) {
		text.slideY(start, end, time);
		return this;
	}

	@Override
	public Label stop() {
		text.stopEffects();
		text.resetEffects();
		return this;
	}
	
}
