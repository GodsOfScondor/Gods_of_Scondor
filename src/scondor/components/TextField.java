package scondor.components;

import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.panels.EffectAble;
import scondor.util.Maths;

public class TextField extends Component implements EffectAble<TextField> {
	
	private Text text;
	private Image bg,fg, cursor;
	private String line = "";
	private boolean focus;
	private int time;
	private boolean cursor_visible ;
	private static final Texture WHITE = new Texture("colors/white");
	private static final Texture BLACK = new Texture("colors/black");
	
	public TextField(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.bg = new Image(WHITE, x-1, y-1, width+2, height+2, -1);
		this.fg = new Image(BLACK, x, y, width, height, -1);
		this.cursor = new Image(WHITE, x, y+2, 1, height-4, -1);
		this.text = new Text("", x+2, y+2, height/10f, 3, -1);
		this.text.setColor(1, 1, 1);
		this.bg.setLayer(0.452f);
		this.fg.setLayer(0.451f);
		this.cursor.setLayer(0.45f);
	}

	@Override
	protected void discard() {
		if (this.bg!=null&&this.fg!=null&&text!=null&&cursor!=null) {
			this.bg.setTransparency(0f);
			this.fg.setTransparency(0f);
			this.text.setTransparency(0f);
			this.cursor.setTransparency(0f);
		}
	}

	@Override
	protected void showup() {
		if (this.bg!=null&&this.fg!=null&&text!=null&&cursor!=null) {
			this.bg.setTransparency(1f);
			this.fg.setTransparency(1f);
			this.text.setTransparency(1f);
			this.cursor.setTransparency(1f);
		}
	}

	@Override
	protected void destroyComp() {
		if (this.bg!=null&&this.fg!=null&&text!=null&&cursor!=null) {
			this.bg.destroy();
			this.fg.destroy();
			this.text.destroy();
			this.cursor.destroy();
		}
	}

	@Override
	protected void refresh() {
		if (Mouse.isButtonTyped(0) && Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + (int)(height*Maths.getScreenRatio())) {
			focus = true;
		} else if (Mouse.isButtonTyped(0)) focus = false;
		
		if (focus && isVisible()) {
			time++;
			time%=50;
			if (time==0) cursor_visible = !cursor_visible;
			this.cursor.setTransparency(cursor_visible ? 1f : 0f);
			if (KeyBoard.hasCurrent()) {
				if (KeyBoard.getCurrent() !='~') {
					if (text.getWidth()+20<width) {
						line = line + KeyBoard.getCurrent();
						text.setText(line);
						text.recreate();
						this.cursor.setX(x+text.getWidth()+5);
					}
				}
			}
			if (KeyBoard.isKeyTyped(KeyBoard.KEY_BACK)) {
				if (line.length()>0) {
					line = line.substring(0, line.length()-1);
					text.setText(line);
					text.recreate();
					this.cursor.setX(x+text.getWidth());
				} else this.cursor.setX(x);
			}
		} else {
			this.cursor.setTransparency(0f);
		}
	}

	@Override
	protected void setPriority(int priority) {
		if (this.bg!=null&&this.fg!=null&&text!=null&&cursor!=null) {
			this.bg.setPriority(priority);
			this.fg.setPriority(priority);
			this.text.setPriority(priority);
			this.cursor.setPriority(priority);
		}
	}
	
	public String getText() {
		return line;
	}

	@Override
	public TextField fade(float start, float end, int time) {
		bg.fade(start, end, time);
		fg.fade(start, end, time);
		cursor.fade(start, end, time);
		text.fade(start, end, time);
		return this;
	}
	
	@Override
	public TextField slideX(int start, int end, int time) {
		bg.slideX(start, end, time);
		fg.slideX(start, end, time);
		cursor.slideX(start, end, time);
		text.slideX(start, end, time);
		return this;
	}
	
	@Override
	public TextField slideY(int start, int end, int time) {
		bg.slideY(start, end, time);
		fg.slideY(start, end, time);
		cursor.slideY(start, end, time);
		text.slideY(start, end, time);
		return this;
	}
	
	@Override
	public TextField stop() {
		bg.stopEffects();
		bg.resetEffects();
		fg.stopEffects();
		fg.resetEffects();
		cursor.stopEffects();
		cursor.resetEffects();
		text.stopEffects();
		text.resetEffects();
		return this;
	}

}
