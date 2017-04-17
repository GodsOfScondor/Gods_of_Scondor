package scondor.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window {
	
	public static void init() {
		try {
			DisplayMode mode = getBestMode();
			
			if (mode.getFrequency()<30) mode = new DisplayMode(1920, 1080);
			else Display.setFullscreen(true);
			
			Display.setDisplayMode(new DisplayMode(1300, 600));
			Display.setFullscreen(false);
			
			Display.setTitle("Gods of Scondor");
			Display.setResizable(false);
			Display.setVSyncEnabled(true);
			Display.create(new PixelFormat(8, 8, 0, 8));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update() {
		Display.sync(100);
		Display.update();
	}
	
	public static void close() {
		Display.destroy();
	}
	
	private static DisplayMode getBestMode() {
		DisplayMode[] modes;
		DisplayMode best = null;
		try {
			modes = Display.getAvailableDisplayModes();
			
			for (int i=0;i<modes.length;i++) {
			    if (best != null) {
			    	if (best.getWidth()*best.getHeight()<modes[i].getWidth()*modes[i].getHeight()) {
			    		best = modes[i];
			    		if (best.getWidth()*best.getHeight()==1920*1080) return best;
			    	}
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
