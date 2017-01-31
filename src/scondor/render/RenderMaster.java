package scondor.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import scondor.font.GlowEffect;
import scondor.font.ShadowEffect;
import scondor.font.Text;
import scondor.font.TextMaster;
import scondor.image.Image;
import scondor.image.Texture;
import scondor.render.font.FontRender;
import scondor.render.image.ImageRender;
import scondor.util.Color;

public class RenderMaster {
	
	private static List<Render> renders = new ArrayList<>();
	private static Priority[] image_priorities = new Priority[5];
	private static Priority[] text_priorities = new Priority[5];
	private static Text text;
	private static float glow = 1;
	private static Text text2;
	
	public static void init() {
		
		TextMaster.init();
		
		for (int n = 0;n<5;n++) image_priorities[n] = new Priority(new ArrayList<Image>());
		for (int n = 0;n<5;n++) text_priorities[n] = new Priority(new ArrayList<Text>());
		
		renders.add(new ImageRender());
		renders.add(new FontRender());
		
		for (Render render : renders) render.setup();
		
		addImage(new Image(new Texture("bg"), 0, 0, 1000, 563), 2);
		text = new Text("Lorem ipsum dolor sit amet", 0, 0, 5f, new GlowEffect(new Color(0, 1, 0), 1));
		text2 = new Text("Lorem ipsum dolor sit amet", 0, 200, 5f, new ShadowEffect(new Color(0, 0, 0), 1));
		text2.setColor(1, 0, 0);
		addText(text,4);
		addText(text2,4);
	}
	
	@SuppressWarnings("unchecked")
	public static void update() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0, 1, 0, 1);
		
		glow = (float) (2f+Math.sin(System.currentTimeMillis()/100));
		System.out.println(glow);
		((GlowEffect)text.getEffect()).setGlow(glow);
		((ShadowEffect)text2.getEffect()).setOffset(glow);
		
		for (int n = 0;n<5;n++) for (Render render : renders) {
			if (render instanceof ImageRender) ((ImageRender)render).render((List<Image>) image_priorities[n].getList());
			if (render instanceof FontRender) ((FontRender)render).render((List<Text>) text_priorities[n].getList());
		}
		
	}
	
	public static void close() {
		for (Render render : renders) render.close();
	} 
	
	@SuppressWarnings("unchecked")
	public static void addText(Text text, int priority) {
		TextMaster.addText(text, priority);
		((List<Text>)text_priorities[priority].getList()).add(text);
	}
	
	@SuppressWarnings("unchecked")
	public static void addImage(Image image, int priority) {
		((List<Image>)image_priorities[priority].getList()).add(image);
	}
	
}
