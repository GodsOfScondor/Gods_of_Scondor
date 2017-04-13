package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.containers.Lobby;
import scondor.containers.Main;
import scondor.containers.PopUp;
import scondor.containers.authentification.Login;
import scondor.containers.authentification.Register;
import scondor.containers.battlefield.Battlefield;
import scondor.containers.deck.DeckChooser;
import scondor.containers.deck.DeckStarter;
import scondor.containers.shop.Shop;

public class Containers {
	
	public static final int LOGIN = 0;
	public static final int REGISTER = 1;
	public static final int MAIN = 2;
	public static final int POPUP = 3;
	public static final int LOBBY = 4;
	public static final int DECK_STARTER = 5;
	public static final int DECK_CHOOSER = 6;
	public static final int DECK_BUILDER = 7;
	public static final int PLAYERGROUND = 8;
	public static final int SHOP = 9;
	

	private static List<Container> containers = new ArrayList<>();
	
	private static PopUp popup;
	
	private static Main main;
	private static Login login;
	private static Register register;
	private static Lobby lobby;
	private static DeckStarter deck_starter;
	private static DeckChooser deck_chooser;
	private static Battlefield playground;
	private static Shop shop;
	
	private static Container current;
	
	public static void init() {
		
		popup = new PopUp();
		
		main = new Main();
		login = new Login();
		register = new Register();
		lobby = new Lobby();
		deck_starter = new DeckStarter();
		deck_chooser = new DeckChooser();
		playground = new Battlefield();
		shop = new Shop();
		
		containers.add(main);
		containers.add(login);
		containers.add(register);
		containers.add(lobby);
		containers.add(deck_starter);
		containers.add(deck_chooser);
		containers.add(playground);
		containers.add(shop);
		
	}
	
	public static void show(Container container) {
		current = container;
		for (Container cont : containers) {
			cont.fade(0.01f, 0, 0);
		}
		container.fade(0, 1, 30);
	}
	
	public static Container getContainer(int id) {
		for (Container cont : containers) if (cont.getID() == id) return cont;
		return null;
	}
	
	public static Main getMain() {
		return main;
	}
	
	public static Login getLogin() {
		return login;
	}
	
	public static Register getRegister() {
		return register;
	}
	
	public static Lobby getLobby() {
		return lobby;
	}
	
	public static DeckChooser getDeckChooser() {
		return deck_chooser;
	}
	
	public static Battlefield getPlayground() {
		return playground;
	}
	
	public static Shop getShop() {
		return shop;
	}
	
	public static DeckStarter getDeckStarter() {
		return deck_starter;
	}
	
	public static void update() {
		if (popup.visibility<0.01f) for (Container cont: containers) cont.update();
		popup.update();
	}
	
	public static void popup(String msg, float r, float g, float b, int container) {
		popup.popup(getContainer(container), msg, r, g, b);
	}

	public static boolean isOpen(Container container) {
		return container == current;
	}
	
	public static void focusField(TextField field) {
		for (Component comp : current.comps) {
			if (comp instanceof TextField) if (comp!=field) ((TextField) comp).setFocus(false);
		}
	}
	
}
