package scondor.components;

import scondor.deck.card.CardData;
import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Texture;
import scondor.panels.EffectAble;

public class Card extends Component implements EffectAble<Card> {
	
	private Image card;
	private Image sprite;
	private Text name;
	private Text description;
	
	private float size;
	private CardData data;
	
	private static final Texture SPRITE_SHEET = new Texture("icons", 32, 32);
	
	public Card(CardData data, int x, int y, float size) {
		super(x, y, (int)(50*size), (int)(80*size));
		
		this.data = data;
		this.size = size;
		
		card = new Image(new Texture("card_gn_common"), x, y, super.width, super.height, -1);
		card.setLayer(0.48f);
		
		sprite = new Image(new Texture(SPRITE_SHEET,0,0), x, y, 0, 0, -1);
		sprite.setLayer(0.481f);
		
		name = new Text("", 0, 0, size*0.3f, 1, -1);
		description = new Text("", 0, 0, size*0.3f, 3, -1);
		
		setData(data);
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
	
	public Card setSize(float size) {
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
		return this;
	}

	@Override
	public Card fade(float start, float end, int time) {
		if (card != null) card.fade(start, end, time);
		if (sprite != null) sprite.fade(start, end, time);
		if (name != null) name.fade(start, end, time);
		if (description != null) description.fade(start, end, time);
		return this;
	}

	@Override
	public Card slideX(int start, int end, int time) {
		if (card != null) card.slideX(start, end, time);
		if (sprite != null) sprite.slideX(start, end, time);
		if (name != null) name.slideX(start, end, time);
		if (description != null) description.slideX(start, end, time);
		return this;
	}

	@Override
	public Card slideY(int start, int end, int time) {
		if (card != null) card.slideY(start, end, time);
		if (sprite != null) sprite.slideY(start, end, time);
		if (name != null) name.slideY(start, end, time);
		if (description != null) description.slideY(start, end, time);
		return this;
	}

	@Override
	public Card stop() {
		return this;
	}
	
	public void setTransparency() {
		
	}
	
	public void setData(CardData data) {
		this.data = data;
		if (hasData()) {
			this.name.setText(data.getName());
			this.description.setText(data.getDescription());
			System.out.println("ID: " + data.getID());
			this.sprite.getTex().setX(data.getID()%32);
			this.sprite.getTex().setY(data.getID()/32);
		} else {
			this.name.setText("swag");
			this.description.setText("");
			this.sprite.getTex().setX(0);
			this.sprite.getTex().setY(0);
		}
		setSize(size);
	}

	public boolean hasData() {
		return data!=null;
	}

	public void setLayer(float layer) {
		card.setLayer(layer-0.0001f);
		sprite.setLayer(layer);
		name.setLayer(layer);
		description.setLayer(layer);
	}

	public void setX(int x) {
		card.setX(x);
		sprite.setX(x);
		name.setXY(x,this.y);
		description.setXY(x,this.y);
	}
	
	public void setY(int y) {
		card.setY(y);
		sprite.setY(x);
		name.setXY(this.x,y);
		description.setXY(this.x,x);
	}

	public void setTransparency(float transparency) {
		card.setTransparency(transparency);
		sprite.setTransparency(transparency);
		name.setTransparency(transparency);
		description.setTransparency(transparency);
	}
	
}
