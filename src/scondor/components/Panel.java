package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;

public class Panel extends Component {
	
	private Image background;
	
	public Panel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void setBackground(Texture tex, int priority) {
		if (background!=null) {
			background.remove();
		}
		this.background = new Image(tex, x, y, width, height, priority);
	}
	
	@Override
	protected void discard() {
		background.setTransparency(0f);
	}

	@Override
	protected void showup() {
		background.setTransparency(1f);
	}
	
	@Override
	protected void destroyComp() {
		background.remove();
	}

	@Override
	protected void update() {}
	
}
