package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.panels.Main;

public class ComponentMaster {
	
	private static List<Container> container = new ArrayList<>();
	private static Main main;
	
	public static void init() {
		main = new Main();
		container.add(main);
	}
	
	public static void update() {
		for (Container cont: container) cont.update();
	}
	
}
