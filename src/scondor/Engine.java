package scondor;

import scondor.content.Connection;
import scondor.image.Images;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.render.RenderMaster;
import scondor.server.Client;
import scondor.util.Utils;
import scondor.util.Window;

public class Engine {
	
	private static Client client;
	private static Connection connection;
	private static boolean started = false;
	private static long ago;
	private static boolean skipping;
	
	
	public static void init() {
		
		client = new Client();
		
		Window.init();
		Images.load();
		RenderMaster.init();
		Utils.init();
		
		KeyBoard.init();
		Mouse.init();
		
		connection = new Connection();
		
	}
	
	public static void update() {
		
		if (!started) {
			connection.start();
			started = true;
		}
		
		ago = System.currentTimeMillis();
		
		KeyBoard.update();
		Mouse.update();
		
		Client.update();
		Utils.update();
		
		RenderMaster.update();
		
		if (!skipping) Window.update();
		
		skipping = (System.currentTimeMillis()-ago) > 9;
		
	}
	
	public static void close() {
		
		connection.close();
		
		Window.close();
		RenderMaster.close();
		Loader.close();
		
	}
	
	public static Client getClient() {
		return client;
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
