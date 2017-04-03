package scondor.panels.deck;

import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.packets.Message;
import scondor.panels.EffectAble;
import scondor.panels.Panels;
import scondor.server.Client;
import scondor.util.Action;
import scondor.util.Messanger;

public class DeckStarter extends Panel {
	
	private Label titel, description;
	private Label wild_name;
	private Picture wild;
	
	public DeckStarter() {
		super(1);
		super.setBackground(new Texture("lobby"));
		titel = new Label("Choose your starter deck:", 100, 100, 3, 1).setEffect(new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f));
		description = new Label("", 0, 900, 2.5f, 1).setEffect(new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f));
		wild_name = new Label("WILD", 138, 690, 5f, 1).setEffect(new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f));
		wild = new Picture(new Texture("card_pack_gn"), 100, 250, 170, 250);
		
		addAction(new Action() {
			public void perform() {
				
				if (isVisible()) {
					selectDeck(wild, 1, "This deck is inspired by the strength of beasts");
				}
				
			}
		});
		
		add(titel);
		add(description);
		
		add(wild);
		add(wild_name);
	}

	private boolean selectDeck(Picture pic, int id, String description) {
		if (pic.isMouseOver()) {
			pic.setX(pic.getCompX()-5);
			pic.setY(pic.getCompY()-5);
			pic.setWidth(pic.getCompWidth()+10);
			pic.setHeight(pic.getCompHeight()+10);
			setDescription(description);
			if (Mouse.isButtonTyped(0)) {
				Client.sendToServer(new Message("starter;"+id));
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

	public static String msgFromServer(int code) {
		return Messanger.nopopup();
	}
	
}
