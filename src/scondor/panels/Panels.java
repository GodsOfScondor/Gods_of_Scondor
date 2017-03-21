package scondor.panels;

import java.util.HashMap;
import java.util.Map;

import scondor.components.Panel;
import scondor.panels.authentification.Login;
import scondor.panels.authentification.Register;
import scondor.panels.deck.DeckBuilder;
import scondor.panels.deck.DeckChooser;
import scondor.panels.deck.DeckStarter;
import scondor.panels.shop.Shop;

public class Panels {
	
	public static final int LOGIN = 0;
	public static final int REGISTER = 1;
	public static final int MAIN = 2;
	public static final int POPUP = 3;
	public static final int LOBBY = 4;
	public static final int SHOP = 5;
	public static final int DECK_CHOOSER = 6;
	public static final int DECK_BUILDER = 7;
	public static final int DECK_STARTER = 8;
	
	public static final int FADEIN = 70;
	public static final int FADEOUT = 30;
	
	private static Map<Integer, Panel> panels = new HashMap<>();
	private static Panel current;
	private static int key;
	
	public static void init() {
		current = null;
		panels.put(LOGIN, new Login());
		panels.put(REGISTER, new Register());
		panels.put(MAIN, new Main());
		panels.put(POPUP, new PopUp());
		panels.put(LOBBY, new Lobby());
		panels.put(SHOP, new Shop());
		panels.put(DECK_CHOOSER, new DeckChooser());
		panels.put(DECK_BUILDER, new DeckBuilder());
		panels.put(DECK_STARTER, new DeckStarter());
	}
	
	public static void show(int key) {
		if (current != null) {
			current.setVisible(false);
			current.swipeOut();
		}
		
		System.out.println(key);
		current = panels.get(key);
		current.show();
		current.swipeIn();
		Panels.key = key;
		
	}
	
	public static void popup(String msg, float r, float g, float b) {
		((PopUp)panels.get(POPUP)).setMSG("BACK", msg, r, g, b, key);
		show(POPUP);
	}
	
	public static void popup(String msg, float r, float g, float b, int panel) {
		((PopUp)panels.get(POPUP)).setMSG("NEXT", msg, r, g, b, panel);
		show(POPUP);
	}

	public static boolean isOpen(int panel) {
		return key == panel;
	}
	
}
