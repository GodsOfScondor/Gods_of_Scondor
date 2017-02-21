package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.panels.EffectAble;
import scondor.util.Maths;

public class CheckBox extends Component implements EffectAble<CheckBox> {
	
	private Image box;
	private boolean select,target;
	private static final int SIZE = 20;
	
	private static final Texture TEX_BOX = new Texture("box");
	private static final Texture TEX_BOX_SELECTED = new Texture("box_sel");
	
	public CheckBox(int x, int y, float size) {
		super(x, y, (int)(30*size), (int)(SIZE*size));
		this.box = new Image(TEX_BOX, x, y, (int)(SIZE*size), (int)(SIZE*size), -1);
		this.box.setTransparency(0.7f);
		this.box.setLayer(0.4f);
	}

	@Override
	protected void discard() {
		if (box!=null) box.setTransparency(0f);
	}

	@Override
	protected void showup() {
		if (box!=null) {
			box.setTransparency(1f);
		}
	}

	@Override
	protected void destroyComp() {
		if (box!=null) box.destroy();
	}

	@Override
	protected void refresh() {
		if (isVisible()) {
			if (Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + height*Maths.getScreenRatio()) {
				
				if (Mouse.isButtonTyped(0)) select = !select;
				
				target = true;
				this.box.setTransparency(1);
				
			} else {
				
				this.box.setTransparency(0.7f);
				target = false;
				
			}
			if (select) box.setTex(TEX_BOX_SELECTED);
			else box.setTex(TEX_BOX);
		}
	}
	
	public boolean isTargeted() {
		return target;
	}
	
	public boolean isSelected() {
		return select;
	}

	@Override
	protected void setPriority(int priority) {
		if (box!=null) box.setPriority(priority);
	}
	
	@Override
	public CheckBox fade(float start, float end, int time) {
		box.fade(start, end, time);
		return this;
	}
	
	@Override
	public CheckBox slideX(int start, int end, int time) {
		box.slideX(start, end, time);
		return this;
	}
	
	@Override
	public CheckBox slideY(int start, int end, int time) {
		box.slideY(start, end, time);
		return this;
	}
	
	@Override
	public CheckBox stop() {
		box.stopEffects();
		box.resetEffects();
		return this;
	}
	
}
