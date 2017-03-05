package scondor.panels;

import java.util.HashMap;
import java.util.Map;

import scondor.components.Panel;

public class Panels {
	
	public static final int LOGIN = 0;
	public static final int REGISTER = 1;
	public static final int MAIN = 2;
	public static final int POPUP = 3;
	public static final int LOBBY = 4;
	public static final int SHOP = 5;
	
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
	}
	
	public static void show(int key) {
		if (current != null) {
			current.setVisible(false);
			current.swipeOut();
		}
		
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
