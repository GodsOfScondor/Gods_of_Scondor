package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.panels.Fadeable;

public abstract class Panel extends Component implements Fadeable {
	
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
	
}
