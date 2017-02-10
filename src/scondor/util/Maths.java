package scondor.util;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Maths {
	
	private static final Vector3f translation = new Vector3f(0,0,0), scale = new Vector3f(0,0,1);
	private static Matrix4f TM = new Matrix4f();
	
	public static Matrix4f createTM(int x, int y, int s_x, int s_y) {
		TM.setIdentity();
		scale.x = s_x/1000f;
		scale.y = s_y/1000f*getScreenRatio();
		translation.x = scale.x - 1 + (x/500f);
		translation.y = -scale.y + 1 - (y/500f);
		Matrix4f.translate(translation, TM, TM);
		Matrix4f.scale(scale, TM, TM);
		return TM;
	}
	
	public static float getScreenRatio() {
		return ((float) Display.getWidth()/Display.getHeight());
	}
	
	public static int diff(int x1, int x2)  {
		if (x1>x2) return x1-x2; 
		else return x2-x1;
	}
	
}
