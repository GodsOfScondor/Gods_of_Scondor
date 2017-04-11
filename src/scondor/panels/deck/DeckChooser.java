package scondor.panels.deck;

import java.util.List;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.IconButton;
import scondor.components.Label;
import scondor.deck.DeckData;
import scondor.image.Images;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;

public class DeckChooser extends Container{

	private static final int PRIORITY = 1;
	private static final int MAX = 5;
	
	private Label titel;
	
	private IconButton[] left_buttons;
	private IconButton[] right_buttons;
	
	private Label[] left_decks;
	private Label[] right_decks;
	
	private int n;
	
	public DeckChooser() {
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
		
		titel = new Label("CHOOSE YOUR DECK:", 10, 10, 5, 1, true);
		
		left_buttons = new IconButton[5];
		right_buttons = new IconButton[5];
		
		left_decks = new Label[5];
		right_decks = new Label[5];
		
		for (n=0;n<MAX;n++) {
			left_decks[n] = new Label("", 325, 330+(115*n), 3, 1, true);
			left_buttons[n] = new IconButton(Images.BUTTON_BORDER, 200, 300+(115*n), 250, 60, new DeckChooseListener(n) {
				public void perform() {
					Client.sendToServer(new Message("lobby;deck;"+super.deck));
					Containers.show(Containers.getLobby());
				}
			}, true).setResize(1.1f);
			right_decks[n] = new Label("", 675, 330+(115*n), 3, 1, true);
			right_buttons[n] = new IconButton(Images.BUTTON_BORDER, 550, 300+(115*n), 250, 60, new DeckChooseListener(5+n) {
				public void perform() {
					Client.sendToServer(new Message("lobby;deck;"+(super.deck)));
					Containers.show(Containers.getLobby());
				}
			}, true).setResize(1.1f);
		}
		
		/*
		 * add components to container
		 */
		
		super.add(titel);
		
		for (n=0;n<MAX;n++) {
			super.add(left_buttons[n]);
			super.add(right_buttons[n]);
			super.add(left_decks[n]);
			super.add(right_decks[n]);
		}
		
		/*
		 * validate
		 */
		
		super.validate();
		
	}
	
	public void setData(List<DeckData> decks) {
		
		System.out.println(decks.size());
		
		for (n=0;n<MAX*2;n++) {
			
			if (decks.size()>n) {
				setText(n, decks.get(n).getName());
			} else {
				setText(n, "");
			}
			
		}
	}
	
	private void setText(int deck, String text) {
		Label label;
		
		if (deck < MAX) {
			label = left_decks[deck%MAX];
			label.setText(text);
			label.setCompX(325-(label.getCompWidth()/2));
		} else {
			label = right_decks[deck%MAX];
			label.setText(text);
			label.setCompX(675-(label.getCompWidth()/2));
		}
	}
	
	private static abstract class DeckChooseListener implements Action {
		
		protected int deck;
		
		public DeckChooseListener(int deck) {
			this.deck = deck;
		}
		
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public int getID() {
		return Containers.DECK_CHOOSER;
	}
	
}
