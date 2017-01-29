package untitled;

import untitled.font.TextMaster;
import untitled.inputs.KeyBoard;
import untitled.inputs.Mouse;
import untitled.render.RenderMaster;
import untitled.util.Window;

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
