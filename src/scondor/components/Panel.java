package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;

public class Panel extends Component {
	
	private Image background;
	private boolean visible;
	
	public Panel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void setBackground(Texture tex, int priority) {
		if (background!=null) {
			background.destroy();
		}
		this.background = new Image(tex, x, y, width, height, priority);
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
	protected void update() {}
	
}
