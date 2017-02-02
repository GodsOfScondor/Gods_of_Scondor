package scondor.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import scondor.font.Text;
import scondor.font.TextMaster;
import scondor.image.Image;
import scondor.render.font.FontRender;
import scondor.render.image.ImageRender;

public class RenderMaster {
	/**
	 * 
	 *  list of all render handlers @Baldinga
	 *  - render priority system
	 *  
	 */
	private static List<Render> renders = new ArrayList<>();
	private static Priority[] image_priorities = new Priority[5];
	private static Priority[] text_priorities = new Priority[5];
	
	/**
	 * 
	 * initializes all render handlers @Baldinga
	 * 
	 */
	public static void init() {
		
		TextMaster.init();
		
		for (int n = 0;n<5;n++) image_priorities[n] = new Priority(new ArrayList<Image>());
		for (int n = 0;n<5;n++) text_priorities[n] = new Priority(new ArrayList<Text>());
		
		renders.add(new ImageRender());
		renders.add(new FontRender());
		
		for (Render render : renders) render.setup();
		
	}
	
	/**
	 * 
	 * updates every render handler @Baldinga
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void update() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 1, 0, 1);
		
		for (int n = 0;n<5;n++) for (Render render : renders) {
			if (render instanceof ImageRender) ((ImageRender)render).render((List<Image>) image_priorities[n].getList());
			if (render instanceof FontRender) ((FontRender)render).render((List<Text>) text_priorities[n].getList());
		}
		
	}
	
	/**
	 * 
	 * closes and cleans up everything
	 * 
	 */
	public static void close() {
		for (Render render : renders) render.close();
	} 
	
	/**
	 * 
	 * adds a new text
	 * should only be used via constructor of Text
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void addText(Text text, int priority) {
		TextMaster.addText(text, priority);
		((List<Text>)text_priorities[priority].getList()).add(text);
	}
	
	/**
	 * 
	 * adds a new image
	 * should only be used via constructor of Image
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void addImage(Image image, int priority) {
		((List<Image>)image_priorities[priority].getList()).add(image);
	}
	
}
