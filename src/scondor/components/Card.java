package scondor.components;

import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Texture;

public class Card extends Component {
	
	private Image card;
	private Image sprite;
	private Text name;
	private Text description;
	
	private float size;
	
	private static final Texture SPRITE_SHEET = new Texture("icons", 32, 32);
	
	public Card(int x, int y, float size) {
		super(x, y, (int)(50*size), (int)(80*size));
		this.size = size;
		card = new Image(new Texture("card_gn_common"), x, y, super.width, super.height, -1);
		card.setLayer(0.48f);
		sprite = new Image(new Texture(SPRITE_SHEET,5,5), x, y, 0, 0, -1);
		sprite.setLayer(0.481f);
		name = new Text("Twisted_Fate", 0, 0, size*0.3f, 1, -1);
		description = new Text("I am a description text for example isuses. A A A A A A A A A A A A A A A A A A A ", 0, 0, size*0.3f, 3, -1);
		setSize(size);
	}

	@Override
	protected void discard() {
		if (card != null) card.setTransparency(0f);
		if (sprite != null) sprite.setTransparency(0f);
		if (name != null) name.setTransparency(0f);
		if (description != null) description.setTransparency(0f);
	}

	@Override
	protected void showup() {
		if (card != null) card.setTransparency(1f);
		if (sprite != null) sprite.setTransparency(1f);
		if (name != null) name.setTransparency(1f);
		if (description != null) description.setTransparency(1f);
	}

	@Override
	protected void destroyComp() {
		if (card != null) card.destroy();
		if (sprite != null) sprite.destroy();
		if (name != null) name.destroy();
		if (description != null) description.destroy();
	}

	@Override
	protected void refresh() {
		
	}

	@Override
	protected void setPriority(int priority) {
		if (card != null) card.setPriority(priority);
		if (sprite != null) sprite.setPriority(priority);
		if (name != null) name.setPriority(priority);
		if (description != null) description.setPriority(priority);
	}
	
	public float getSize() {
		return size;
	}
	
	public void setSize(float size) {
		this.size = size;
		this.card.setWidth((int)(50*size));
		this.card.setHeight((int)(80*size));
		this.sprite.setX(x+(int)(size*5));
		this.sprite.setY(y+(int)(size*10));
		this.sprite.setWidth((int) (this.card.getWidth()*0.8f));
		this.sprite.setHeight((int) (this.card.getHeight()*0.43f));
		this.name.setSize(size*0.3f);
		this.name.setXY(x+(int)((size*26)-(this.name.getWidth()*0.5f)), y+(int)(size*71));
		this.name.recreate();
		this.description.setXY(x+(int)((size*8)), y+(int)(size*83));
		this.description.setLineSize((int) (size*36));
		this.description.setSize(size*0.3f);
		this.description.recreate();
	}
	
}
