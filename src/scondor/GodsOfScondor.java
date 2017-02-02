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
	public static void main(String[] args) {
		
		Engine.init();
		Game.init();
		
		while (!Display.isCloseRequested()&&!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			
			Engine.update();
			Game.update();
			
		}
		
	}
	
}
