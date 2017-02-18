package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.panels.EffectAble;
import scondor.panels.SwipeAble;

public abstract class Panel extends Component implements SwipeAble, EffectAble<Panel> {
	
	private Image background;
	private int priority;
	
	public Panel(int x, int y, int width, int height, int priority) {
		super(x, y, width, height);
		this.priority = priority;
	}
	
	public Panel setBackground(Texture tex) {
		if (background!=null) {
			background.destroy();
		}
		this.background = new Image(tex, x, y, width, height, -1);
		return this;
	}
	
	public void show() {
		validate(priority);
		setVisible(true);
	}
	
	@Override
	protected void discard() {
		if (background!=null) background.setTransparency(0f);
	}

	@Override
	protected void showup() {
		if (background!=null) background.setTransparency(1f);
	}
	
	@Override
	protected void destroyComp() {
		if (background!=null) background.destroy();
	}

	@Override
	protected void refresh() {}

	@Override
	protected void setPriority(int priority) {
		background.setPriority(priority);
	}
	
	@Override
	public Panel fade(float start, float end, int time) {
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(start, end, time);
		return this;
	}
	
	@Override
	public Panel slideX(int start, int end, int time) {
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).slideX(start, end, time);
		return this;
	}
	
	@Override
	public Panel slideY(int start, int end, int time) {
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).slideY(start, end, time);
		return this;
	}
	
	@Override
	public Panel stop() {
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).stop();
		return this;
	}
	
}
