package scondor.components;

import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.util.Maths;

public class PasswordField extends TextField {

	public PasswordField(int x, int y, int width, int height, boolean depending) {
		super(x, y, width, height, depending);
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
						text.setText(generateHidedText(line.length()));
						text.recreate();
						this.cursor.setX(x+text.getWidth()+5-(!line.isEmpty()?5:0));
					}
				}
			}
			if (KeyBoard.isKeyTyped(KeyBoard.KEY_BACK)) {
				if (line.length()>0) {
					line = line.substring(0, line.length()-1);
					text.setText(generateHidedText(line.length()));
					text.recreate();
					this.cursor.setX(x+text.getWidth()+(line.isEmpty()?20:0));
					if (line.length()==0) this.cursor.setX(x);
				} else this.cursor.setX(x);
			}
		} else {
			this.cursor.setTransparency(0f);
		}
	}
	
	private String generateHidedText(int length) {
		String str = "";
		for (int n=0;n<length;n++) str = str+"*";
		return str;
	}

}
