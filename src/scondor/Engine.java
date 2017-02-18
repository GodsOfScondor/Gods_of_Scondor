package scondor;

import scondor.components.ComponentMaster;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.panels.Panels;
import scondor.render.RenderMaster;
import scondor.server.Client;
import scondor.util.Utils;
import scondor.util.Window;

public class Engine {
	
	private static Client client;
	
	public static void init() {
		
//		client = new Client();
//		
//		try { Thread.sleep(5000); }
//		catch (InterruptedException e) { e.printStackTrace(); }
//		
//		Client.send(new Message("Hallo!"));
		
		Window.init();
		RenderMaster.init();
		ComponentMaster.init();
		Utils.init();
		Panels.init();
		
		KeyBoard.init();
		Mouse.init();
		
		Panels.show(Panels.LOGIN);
		Panels.show(Panels.MAIN);
		
	}
	
	public static void update() {
		KeyBoard.update();
		Mouse.update();
		
		Utils.update();
		ComponentMaster.update();
		
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
