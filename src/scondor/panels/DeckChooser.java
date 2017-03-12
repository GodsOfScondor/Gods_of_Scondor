package scondor.panels;

import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;

public class DeckChooser extends Panel {

	private Label text, description;
	
	private Picture wild;
	private Label wild_name;
	
	public DeckChooser() {
		super(1);
		super.setBackground(new Texture("lobby"));
		text = new Label("Choose your deck: ", 100, 100, 5, 1);
		description = new Label(" ", 000, 900, 3, 1);
		
		wild = new Picture(new Texture("card_pack_gn"), 100, 250, 180, 250);
		
		addAction(new Action() {
			public void perform() {
				if (
						
					!selectDeck(wild, 0, "This deck is inspired by the strengh of wild beast.")
					
				) {
					setDescription("");
				}
			}
		});
		
		add(wild);
		add(description);
		add(text);
	}
	
	private boolean selectDeck(Picture pic, int id, String description) {
		if (pic.isMouseOver()) {
			pic.setX(pic.getCompX()-5);
			pic.setY(pic.getCompY()-5);
			pic.setWidth(pic.getCompWidth()+10);
			pic.setHeight(pic.getCompHeight()+10);
			setDescription(description);
			if (Mouse.isButtonTyped(0)) {
				Client.send(new Message("starter;"+id));
			}
		} else {
			pic.setX(pic.getCompX());
			pic.setY(pic.getCompY());
			pic.setWidth(pic.getCompWidth());
			pic.setHeight(pic.getCompHeight());
		}
		return pic.isMouseOver();
	}
	
	private void setDescription(String desc) {
		description.setText(desc);
		description.setXY(500-(description.getCompWidth()/2), description.getCompY());
	}
	
	@Override
	public void swipeIn() {
		fade(0, 1, Panels.FADEIN);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
	}

	@Override
	public void swipeOut() {
		fade(1, 0, Panels.FADEOUT);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
	}
	
}
