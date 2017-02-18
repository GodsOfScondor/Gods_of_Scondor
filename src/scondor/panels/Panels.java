package scondor.panels;

import java.util.HashMap;
import java.util.Map;

import scondor.components.Panel;

public class Panels {
	
	public static final int LOGIN = 0;
	public static final int REGISTER = 1;
	public static final int MAIN = 2;
	
	private static Map<Integer, Panel> panels = new HashMap<>();
	private static Panel current;
	
	public static void init() {
		current = null;
		panels.put(LOGIN, new Login());
		panels.put(MAIN, new Main());
	}
	
	public static void show(int panel) {
		if (current != null) {
			current.setVisible(false);
			current.swipeOut();
		}
		
		current = panels.get(panel);
		current.show();
		current.swipeIn();
		
	}
	
}
