package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.panels.Main;
import scondor.panels.PopUp;
import scondor.panels.authentification.Login;
import scondor.panels.authentification.Register;

public class Containers {
	
	public static final int LOGIN = 0;
	public static final int REGISTER = 1;
	public static final int MAIN = 2;
	public static final int POPUP = 3;
	public static final int LOBBY = 4;
	public static final int DECK_STARTER = 5;
	

	private static List<Container> containers = new ArrayList<>();
	
	private static PopUp popup;
	
	private static Main main;
	private static Login login;
	private static Register register;
	
	private static Container current;
	
	public static void init() {
		
		popup = new PopUp();
		
		main = new Main();
		login = new Login();
		register = new Register();
		
		containers.add(main);
		containers.add(login);
		containers.add(register);
		
	}
	
	public static void show(Container container) {
		current = container;
		for (Container cont : containers) cont.fade(1, 0, 0);
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
		System.out.println("know");
		for (Component comp : current.comps) {
			if (comp instanceof TextField) if (comp!=field) ((TextField) comp).setFocus(false);
		}
	}
	
}