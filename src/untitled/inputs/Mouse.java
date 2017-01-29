package untitled.inputs;

import org.lwjgl.opengl.Display;

public class Mouse {
	
	private static final int M_LEFT = 0;
	private static final int M_RIGHT = 1;
	public static int X = 0;
	public static int Y = 0;
	private static int n;
	private static boolean[] pressed = new boolean[2];
	private static boolean[] typed = new boolean[2];
	private static boolean[] released = new boolean[2];
	private static int wheel;
	private static boolean debug = true;
	
	public static void init() {
		for (n=0;n<2;n++) released[n] = true;
	}
	
	public static void update() {
		wheel = org.lwjgl.input.Mouse.getDWheel();
		for (n=0;n<2;n++) typed[n] = false;
		
		X = (int) (org.lwjgl.input.Mouse.getX()*(1000f/Display.getWidth()));
		Y = (int) ((Display.getHeight()-org.lwjgl.input.Mouse.getY()*(1000f/Display.getWidth())));
		pressed[M_LEFT] = org.lwjgl.input.Mouse.isButtonDown(0);
		pressed[M_RIGHT] = org.lwjgl.input.Mouse.isButtonDown(1);
		
		for (n=0;n<2;n++) {
			if (pressed[n] && released[n]) {
				released[n] = false;
				typed[n] = true;
			} else if (!pressed[n]) released[n] = true;
		}
		if (debug && Mouse.isButtonTyped(0)) {
			System.out.println(Mouse.X+"|"+Mouse.Y);
		}
		
	}
	
	public static boolean isButtonPressed(int button) {
		return pressed[button];
	}
	
	public static boolean isButtonTyped(int button) {
		return typed[button];
	}
	
	public static int getDWheel() {
		return wheel;
	}
	
}
