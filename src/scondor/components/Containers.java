package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.panels.Main;
import scondor.panels.PopUpPanel;
import scondor.panels.authentification.Login;
import scondor.panels.authentification.Register;

public class Containers {
	
	private static List<Container> containers = new ArrayList<>();
	
	private static PopUpPanel popup;
	
	private static Main main;
	private static Login login;
	private static Register register;
	
	private static Container current;
	
	public static void init() {
		
		popup = new PopUpPanel();
		
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
		if (popup.isInVisible()) for (Container cont: containers) cont.update();
		popup.update();
	}
	
	public static void popup(String msg, float r, float g, float b) {
		popup.popup(current, msg, r, g, b);
	}
	
	public static void popup(String msg, float r, float g, float b, int container) {
		popup.popup(current, msg, r, g, b);
	}
	
}
