package scondor.panels.deck;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.IconButton;
import scondor.components.Label;
import scondor.image.Images;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;
import scondor.util.Messanger;

public class DeckStarter extends Container {

	private static final int PRIORITY = 1;
	private IconButton wild;
	private Label description;
	private Label titel;
	
	public DeckStarter() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		super.setBackground(Images.WALLPAPER_LOBBY);
		
		/*
		 * create effects and colors
		 */
		
		/*
		 * create components
		 */

		wild = new IconButton(Images.CARD_PACK_WILD, 50, 400, 150, 200, new Action() {
			public void perform() {
				Client.sendToServer(new Message("starter;1"));
			}
		}, true);
		wild.setResize(1.2f);
		
		description = new Label("TEMPLATE", 500, 930, 2.5f, 1, false);
		titel = new Label("CHOOSE YOUR STARTER DECK:", 10, 10, 5f, 1, true);
		
		/*
		 * add components to container
		 */
		
		super.add(wild);
		super.add(description);
		super.add(titel);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}
	
	private void setDescription(String description) {
		this.description.setText(description);
		this.description.setCompX(500-(this.description.getCompWidth()/2));
	}

	@Override
	public void refresh() {
		description.fade(0, 1, 0);
		if (wild.isMouseOver()) setDescription("This deck combines the strength of WILD creatures. \"Together we are stronger!\"");
		else setDescription("");
	}

	@Override
	public int getID() {
		return Containers.DECK_STARTER;
	}

	public static String msgFromServer(int code) {
		return Messanger.build("Recieved starter deck!", Containers.DECK_STARTER, 0, 1, 0);
	}
	
	
	
}
