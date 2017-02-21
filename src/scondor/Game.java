package scondor;

import scondor.components.ComponentMaster;
import scondor.panels.Panels;
import scondor.panels.start.Connector;

public class Game {
	
	public static void init() {
		
		ComponentMaster.init();
		Panels.init();
		
		Connector.init();
		
	}
	
	public static void update() {
		ComponentMaster.update();
	}
	
	public static void close() {
		
	}
	
}
