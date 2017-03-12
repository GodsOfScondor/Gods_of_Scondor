package scondor;

import scondor.content.Connection;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.render.RenderMaster;
import scondor.server.Client;
import scondor.util.Utils;
import scondor.util.Window;

public class Engine {
	
	private static Client client;
	private static Connection connection;
	
	public static void init() {
		
		client = new Client();
		
		Window.init();
		RenderMaster.init();
		Utils.init();
		
		KeyBoard.init();
		Mouse.init();
		
		connection = new Connection();
		connection.start();
		
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
