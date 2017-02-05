package scondor;

import scondor.font.Text;
import scondor.image.Image;
import scondor.image.Texture;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.render.RenderMaster;
import scondor.server.Client;
import scondor.util.Window;

public class Engine {
	
	private static Client client;
	
	public static void init() {
		
		client = new Client();
		
		Window.init();
		RenderMaster.init();
		
		KeyBoard.init();
		Mouse.init();
		
		new Image(new Texture("bg"), 0, 0, 1000, 1000, 4);
		new Text("Hallo du da!", 0, 0, 1, 4).setColor(1, 1, 1);
		
	}
	
	public static void update() {
		
		KeyBoard.update();
		Mouse.update();
		
		RenderMaster.update();
		Window.update();
	}
	
	public static void close() {
		Window.cleanUp();
		RenderMaster.close();
		Loader.close();
	}
	
	public static Client getClient() {
		return client;
	}
	
}
