package scondor.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window {
	
	public static void init() {
		try {
			System.out.println(getBestMode().getWidth());
			Display.setDisplayMode(getBestMode());
			Display.setFullscreen(true);
			Display.setTitle("Untitled Engine");
			Display.setResizable(false);
			Display.create(new PixelFormat());
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update() {
		Display.sync(100);
		Display.update();
	}
	
	public static void cleanUp() {
		Display.destroy();
	}
	
	private static DisplayMode getBestMode() {
		DisplayMode[] modes;
		DisplayMode best = null;
		try {
			modes = Display.getAvailableDisplayModes();
			
			for (int i=0;i<modes.length;i++) {
			    if (best != null) {
			    	if (best.getWidth()*best.getHeight()<modes[i].getWidth()*modes[i].getHeight()) best = modes[i];
			    } else {
			    	best = modes[i];
			    }
			}
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		return best;
	}
	
}
