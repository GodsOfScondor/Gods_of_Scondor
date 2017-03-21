package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.KeyBoard;
import scondor.panels.EffectAble;
import scondor.panels.SwipeAble;
import scondor.util.Maths;

public abstract class Panel extends Component implements SwipeAble, EffectAble<Panel> {
	
	private Image background;
	private int priority;
	
	public Panel(int x, int y, int width, int height, int priority) {
		super(x, y, width, height);
		this.priority = priority;
	}
	
	public Panel(int priority) {
		super(0, 0, 1000, 1 + (int) (1000/Maths.getScreenRatio()));
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
	protected void refresh() {
		if (isVisible()) if (KeyBoard.isKeyTyped(KeyBoard.KEY_TAB)) {
			TextField current = null;
			TextField next = null;
			TextField first = null;
			
			for (Component comp : comps) if (comp instanceof TextField) {
				if (((TextField)comp).isFocused()) {
					current = (TextField) comp;
					break;
				}
			}
			
			for (Component comp : comps) if (comp instanceof TextField) {
				if (current==comp) {
					((TextField)comp).setFocus(false);
					continue;
				}
				if (current!=null) {
					if (comp.getCompY()>=current.getCompY()) {
						if (next!=null) {
							if (comp.getCompY() < next.getCompY()) {
								next = (TextField) comp;
							}
						} else {
							next = (TextField) comp;
						}
					}
				} else {
					current = (TextField) comp;
					next = (TextField) comp;
				}
			}
			
			if (next!=null) next.setFocus(true);
			else {
				for (Component comp : comps) if (comp instanceof TextField) {
					if (first!=null) {
						if (comp.getCompY() < first.getCompY()) first = (TextField) comp;
					} else {
						first = (TextField) comp;
					}
				}
				first.setFocus(true);
			}
			
		}
	}

	@Override
	protected void setPriority(int priority) {
		if (background!=null) background.setPriority(priority);
	}
	
	@Override
	public Panel fade(float start, float end, int time) {
		if (background!=null) background.fade(start, end, time);
		return this;
	}
	
	@Override
	public Panel slideX(int start, int end, int time) {
		if (background!=null) background.slideX(start, end, time);
		return this;
	}
	
	@Override
	public Panel slideY(int start, int end, int time) {
		if (background!=null) background.slideY(start, end, time);
		return this;
	}
	
	@Override
	public Panel stop() {
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).stop();
		return this;
	}
	
}
