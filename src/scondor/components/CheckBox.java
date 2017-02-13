package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.util.Maths;

public class CheckBox extends Component {
	
	private Image box;
	private boolean select,target;
	private static final int SIZE = 20;
	
	private static final Texture TEX_BOX = new Texture("box");
	private static final Texture TEX_BOX_SELECTED = new Texture("box_sel");
	private static final Texture TEX_BOX_TARGET = new Texture("box_target");
	
	public CheckBox(int x, int y, float size) {
		super(x, y, (int)(30*size), (int)(SIZE*size));
		this.box = new Image(TEX_BOX, x, y, (int)(SIZE*size), (int)(SIZE*size), -1);
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
		if (Mouse.X >= x && Mouse.X <= x + width && Mouse.Y >= y && Mouse.Y <= y + height*Maths.getScreenRatio()) {
			if (!select) box.setTex(TEX_BOX_TARGET);
			target = true;
			if (Mouse.isButtonTyped(0)) select = !select;
		} else {
			if (!select) box.setTexture(TEX_BOX);
			target = false;
		}
		if (select) box.setTex(TEX_BOX_SELECTED);
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

}
