package scondor.components;

import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Images;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.util.Maths;

public class TextField extends Component {

	protected Text text;
	protected Image bg,fg, cursor;
	protected boolean focus;
	protected boolean cursor_visible, over;
	protected int time;
	protected String line = "";
	protected int offset_x, offset_y;
	
	public TextField(int x, int y, int width, int height, boolean depending) {
		super(x, y, width, height, depending);
		this.bg = new Image(Images.COLOR_WHITE, x-1, y-1, width+2, height+2, -1);
		this.fg = new Image(Images.COLOR_BLACK, x, y, width, height, -1);
		this.cursor = new Image(Images.COLOR_WHITE, x, y+2, 1, height-4, -1);
		this.text = new Text("", x+2, y+2, height/10f, 3, -1);
		this.text.setColor(1, 1, 1);
		this.bg.setLayer(0.49f);
		this.fg.setLayer(0.48f);
		this.cursor.setLayer(0.47f);
	}
	
	public String getText() {
		return line;
	}
	
	public boolean isFocused() {
		return focus;
	}
	
	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	@Override
	public void fade(float start, float end, int duration) {
		cursor.fade(start, end, duration);
		bg.fade(start, end, duration);
		fg.fade(start, end, duration);
		text.fade(start, end, duration);
	}

	@Override
	protected void destroy() {
		cursor.destroy();
		bg.destroy();
		fg.destroy();
		text.destroy();
	}

	@Override
	protected void update() {
		
		over = (Mouse.isButtonTyped(0) && Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y/Maths.getScreenRatio() <= (y + height*Maths.getScreenRatio()));
		if (over) {
			Containers.focusField(this);
			focus = true;
		}
		else if (Mouse.isButtonTyped(0)) focus = false;
		
		if (focus) {
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
						this.cursor.setX(x+text.getWidth()+5-(!line.isEmpty()?5:0));
					}
				}
			}
			if (KeyBoard.isKeyTyped(KeyBoard.KEY_BACK)) {
				if (line.length()>0) {
					line = line.substring(0, line.length()-1);
					text.setText(line);
					text.recreate();
					this.cursor.setX(x+text.getWidth()+(line.isEmpty()?20:0));
					if (line.length()==0) this.cursor.setX(x);
				} else this.cursor.setX(x);
			}
		} else {
			this.cursor.setTransparency(0f);
		}
	}

	@Override
	protected void fade(float visibility) {
		if (super.isDepending()) {
			text.setTransparency(visibility);
			cursor.setTransparency(visibility);
			bg.setTransparency(visibility);
			fg.setTransparency(visibility);
		}
	}

	@Override
	protected void validate(int priority) {
		text.validate(priority);
		cursor.validate(priority);
		bg.validate(priority);
		fg.validate(priority);
	}
	
	@Override
	public void setCompX(int x) {
		offset_x = x;
		cursor.setX(offset_x+cursor.getX());
		bg.setX(offset_x+bg.getX());
		fg.setX(offset_x+fg.getX());
		text.setX(offset_x+text.getX());
		super.setCompX(x);
	}
	
	@Override
	public void setCompY(int y) {
		offset_y = y;
		cursor.setY(offset_y+cursor.getY());
		bg.setY(offset_y+bg.getY());
		fg.setY(offset_y+fg.getY());
		text.setY(offset_y+text.getY());
		super.setCompY(y);
	}
	
}
