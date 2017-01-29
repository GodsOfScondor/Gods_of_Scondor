package untitled;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Zelda {
	
	/**
	 * 
	 * @author Bernhard Sccharrer
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
