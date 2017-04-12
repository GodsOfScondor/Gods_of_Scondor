package scondor.components;

import scondor.deck.card.CardData;
import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Images;

public class Card extends Component {
	
	private Image layout;
	private Image image;
	private Text name;
	private Text description;
	
	private CardData data;
	private float size;
	
	public Card(CardData data, int x, int y, float size, boolean depending) {
		super(x, y, 0, 0, depending);
		
		this.data = data;
		this.size = size;
		
		layout = new Image(Images.LAYOUT_GREEN_COMMON, 0,0,0,0, -1);
		image = new Image(Images.TEST_IMG, 0,0,0,0, -1);
		name = new Text("", 0,0,0, 1, -1);
		description = new Text("", 0,0,0, 3, -1);
		image.setLayer(0.49f);
		layout.setLayer(0.48f);
		name.setLayer(0.47f);
		description.setLayer(0.46f);
		
		setSize(x,y,size);
	}
	
	public void changeData(CardData data) {
		
	}
	
	public void setSize(int x, int y, float size) {
		
		this.size = size;
		
		super.setCompX(x);
		super.setCompY(y);
		super.setCompWidth((int)(80*size));
		super.setCompHeight((int)(120*size));
		
		layout.setX(x);
		layout.setY(y);
		layout.setWidth(super.getCompWidth());
		layout.setHeight(super.getCompHeight());
		
		image.setX(x+(int)(6f*size));
		image.setY(y+(int)(12*size));
		image.setWidth((int) (super.getCompWidth()*0.85));
		image.setHeight((int) (super.getCompHeight()*0.43));
		
		if (data != null) name.setText(data.getName());
		name.setSize(size*0.7f);
		name.setX(x+(int)(42*size)-(name.getWidth()/2));
		name.setY(y+(int)(78*size));
		
		if (data != null) description.setText(data.getDescription());
		description.setSize(size*0.3f);
		description.setX(x+(int)(10*size));
		description.setY(y+(int)(92*size));
		description.setLineSize((int)(super.getCompWidth()*0.85));
	}
	
	public float getSize() {
		return size;
	}
	
	public void setLayer(float layer) {
		layout.setLayer(layer-0.001f);
		image.setLayer(layer);
		name.setLayer(layer-0.002f);
		description.setLayer(layer-0.003f);
	}
	
	public boolean hasData() {
		return data!=null;
	}

	@Override
	public void fade(float start, float end, int duration) {
		layout.fade(start, end, duration);
		image.fade(start, end, duration);
		name.fade(start, end, duration);
		description.fade(start, end, duration);
	}

	@Override
	protected void destroy() {
		layout.destroy();
		image.destroy();
		name.destroy();
		description.destroy();
	}

	@Override
	protected void update() {
		
	}

	@Override
	protected void fade(float visibility) {
		if (super.isDepending()) {
			layout.setTransparency(visibility);
			image.setTransparency(visibility);
			name.setTransparency(visibility);
			description.setTransparency(visibility);
		}
	}

	@Override
	protected void validate(int priority) {
		layout.validate(priority);
		image.validate(priority);
		name.validate(priority);
		description.validate(priority);
	}
	
	@Override
	public void setCompX(int x) {
		setSize(x, y, size);
		super.setCompX(x);
	}
	
	@Override
	public void setCompY(int y) {
		setSize(x, y, size);
		super.setCompY(y);
	}
	
}
