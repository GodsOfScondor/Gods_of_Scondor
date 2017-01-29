package untitled.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import untitled.render.font.FontRender;
import untitled.render.image.ImageRender;

public class RenderMaster {
	
	private static List<Render> renders = new ArrayList<>();
	
	public static void init() {
		renders.add(new ImageRender());
		//renders.add(new FontRender());
		
		for (Render render : renders) render.setup();
	}
	
	public static void update() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 1, 0, 1);
		
		for (Render render : renders) render.render();
		
	}
	
	public static void close() {
		for (Render render : renders) render.close();
	} 
	
}
