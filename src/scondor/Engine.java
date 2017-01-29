package scondor;

import scondor.font.TextMaster;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.render.RenderMaster;
import scondor.util.Window;

public class Engine {
	
	public static void init() {
		
//		TextMaster.init();
		
		Window.init();
		RenderMaster.init();
		KeyBoard.init();
		Mouse.init();
	}
	
	public static void update() {
		
		KeyBoard.update();
		Mouse.update();
		
		RenderMaster.update();
		Window.update();
	}
	
	public static void close() {
		Window.cleanUp();
		RenderMaster.close();
		Loader.close();
	}
	
}
