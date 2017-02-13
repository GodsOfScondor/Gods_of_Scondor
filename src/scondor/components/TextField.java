package scondor.components;

import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.KeyBoard;

public class TextField extends Component {
	
	private Text text;
	private Image bg,fg;
	private String line = "";
	private static final Texture WHITE = new Texture("colors/white");
	private static final Texture BLACK = new Texture("colors/black");
	
	public TextField(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.bg = new Image(WHITE, x-1, y-1, width+2, height+2, -1);
		this.fg = new Image(BLACK, x, y, width, height, -1);
		this.text = new Text("", x+2, y+2, height/10f, null, -1);
		this.text.setColor(1, 1, 1);
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
		if (isVisible()) {
			if (KeyBoard.hasCurrent()) {
				if (KeyBoard.getCurrent() !='~') {
					if (text.getWidth()+20>width) return;
					line  = line + KeyBoard.getCurrent();
					text.setText(line);
					text.recreate();
				}
			}
			if (KeyBoard.isKeyTyped(KeyBoard.KEY_BACK) && line.length()>0) {
				line = line.substring(0, line.length()-1);
				text.setText(line);
				text.recreate();
			}
		}
	}

	@Override
	protected void setPriority(int priority) {
		if (this.bg!=null&&this.fg!=null&&text!=null) {
			this.bg.setPriority(priority);
			this.fg.setPriority(priority);
			this.text.setPriority(priority);
		}
	}
	
	public String getText() {
		return line;
	}

}
