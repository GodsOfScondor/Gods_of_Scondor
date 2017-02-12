package scondor.components;

import scondor.image.Image;
import scondor.image.Texture;

public class CheckBox extends Component {
	
	private Image box;
	private Image box_sel;
	
	private static Texture tex_box = new Texture("box");
	private static Texture tex_box_sel = new Texture("box_sel");
	
	public CheckBox(int x, int y, float size) {
		super(x, y, (int)(30*size), (int)(30*size));
		this.box = new Image(tex_box, x, y, (int)(30*size), (int)(30*size), -1);
		this.box_sel = new Image(tex_box_sel, x, y, (int)(30*size), (int)(30*size), -1);
	}

	@Override
	protected void discard() {
		if (box!=null) box.setTransparency(0f);
		if (box_sel!=null) box_sel.setTransparency(0f);
	}

	@Override
	protected void showup() {
		if (box!=null) box.setTransparency(1f);
		if (box_sel!=null) box_sel.setTransparency(1f);
	}

	@Override
	protected void destroyComp() {
		if (box!=null) box.destroy();
		if (box_sel!=null) box_sel.destroy();
	}

	@Override
	protected void refresh() {
		// TODO stuff
	}

	@Override
	protected void setPriority(int priority) {
		if (box!=null) box.setPriority(priority);
		if (box_sel!=null) box_sel.setPriority(priority);
	}

}
