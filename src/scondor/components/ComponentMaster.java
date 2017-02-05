package scondor.components;

import java.util.ArrayList;
import java.util.List;

public class ComponentMaster {
	
	protected static List<Component> comps = new ArrayList<>();
	
	public static void init() {
		
	}
	
	public static void update() {
		for (Component comp: comps) comp.update();
	}
	
}
