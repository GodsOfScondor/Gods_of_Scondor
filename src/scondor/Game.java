package scondor;

import scondor.components.ComponentMaster;
import scondor.content.Connection;
import scondor.panels.Panels;

public class Game {
	
	public static void init() {
		
		ComponentMaster.init();
		Panels.init();
		
	}
	
	public static void update() {
		ComponentMaster.update();
	}
	
	public static void close() {
		
	}
	
}
