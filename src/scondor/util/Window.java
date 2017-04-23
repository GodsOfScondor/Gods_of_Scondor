package scondor.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	public static void init() {
		try {
//			Display.setFullscreen(true);
			Display.setDisplayMode(new DisplayMode(600, 400));

			Display.setTitle("Gods of Scondor");
			Display.setResizable(false);
			Display.setVSyncEnabled(false);
			Display.create(new PixelFormat(8, 8, 0, 8));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public static void update() {
		Display.sync(500);
		Display.update();
	}

	public static void close() {
		Display.destroy();
	}
}
