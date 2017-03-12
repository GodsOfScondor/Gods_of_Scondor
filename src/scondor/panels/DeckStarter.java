package scondor.panels;

import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;

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
				
				if (wild.isMouseOver()) {
					resize(wild, true);
					setDescription("This deck deals with the strengh beasties.");
					if (Mouse.isButtonTyped(0)) {
						Client.send(new Message("starter;0"));
					}
				} else {
					resize(wild, false);
				}
				
			}
		});
		
		add(titel);
		add(description);
		
		add(wild);
		add(wild_name);
	}

	private void setDescription(String desc) {
		description.setText(desc);
		description.setXY(500-(description.getCompWidth()/2), description.getCompY());
	}
	
	private void resize(Picture picture, boolean bold) {
		if (bold) {
			wild.setX(wild.getCompX()-5);
			wild.setY(wild.getCompY()-5);
			wild.setWidth(wild.getCompWidth()+10);
			wild.setHeight(wild.getCompHeight()+10);
		} else {
			wild.setX(wild.getCompX());
			wild.setY(wild.getCompY());
			wild.setWidth(wild.getCompWidth());
			wild.setHeight(wild.getCompHeight());
		}
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
		return null;
	}
	
}
