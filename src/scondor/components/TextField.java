package scondor.components;

import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Texture;

public class TextField extends Component {
	
	private Text text;
	private Image bg,fg;
	private static final Texture WHITE = new Texture("colors/white");
	private static final Texture BLACK = new Texture("colors/black");
	
	public TextField(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.bg = new Image(WHITE, x-1, y-1, width+2, height+2, -1);
		this.fg = new Image(BLACK, x, y, width, height, -1);
		this.bg.setLayer(0.451f);
		this.fg.setLayer(0.45f);
	}

	@Override
	protected void discard() {
		if (this.bg!=null&&this.fg!=null&&text!=null) {
			this.bg.setTransparency(0f);
			this.fg.setTransparency(0f);
			this.text.setTransparency(0f);
		}
	}

	@Override
	protected void showup() {
		if (this.bg!=null&&this.fg!=null&&text!=null) {
			this.bg.setTransparency(1f);
			this.fg.setTransparency(1f);
			this.text.setTransparency(1f);
		}
	}

	@Override
	protected void destroyComp() {
		if (this.bg!=null&&this.fg!=null&&text!=null) {
			this.bg.destroy();
			this.fg.destroy();
			this.text.destroy();
		}
	}

	@Override
	protected void refresh() {
		
	}

	@Override
	protected void setPriority(int priority) {
		if (this.bg!=null&&this.fg!=null&&text!=null) {
			this.bg.setPriority(priority);
			this.fg.setPriority(priority);
			this.text.setPriority(priority);
		}
	}

}
