package scondor;

import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.render.RenderMaster;
import scondor.server.Client;
import scondor.util.Utils;
import scondor.util.Window;

public class Engine {
	
	private static Client client;
	
	public static void init() {
		
		client = new Client();
		
		Window.init();
		RenderMaster.init();
		Utils.init();
		
		KeyBoard.init();
		Mouse.init();
		
	}
	
	public static void update() {
		KeyBoard.update();
		Mouse.update();
		
		Client.update();
		Utils.update();
		
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
