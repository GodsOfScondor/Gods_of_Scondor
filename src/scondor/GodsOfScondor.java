package scondor;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

//import scondor.server.Client;

public class GodsOfScondor {
	
	/**
	 * 
	 * @author Bernhard Scharrer
	 * 
	 */
	
	private static boolean close;
	
	public static void main(String[] args) {
		
		Engine.init();
		Game.init();
		
		while (!close && !Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			
			Engine.update();
			Game.update();
			
		}
		
		Engine.close();
		Game.close();
		
		System.exit(0);
		
	}
	
	public static void close() {
		close = true;
	}
	
}
