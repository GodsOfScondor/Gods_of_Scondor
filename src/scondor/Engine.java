package scondor;

import scondor.components.Button;
import scondor.components.ComponentMaster;
import scondor.components.Panel;
import scondor.font.OutlineEffect;
import scondor.image.Texture;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.render.RenderMaster;
import scondor.server.Client;
import scondor.util.Action;
import scondor.util.Color;
import scondor.util.Maths;
import scondor.util.Window;

public class Engine {
	
	private static Client client;
	
	public static void init() {
		
//		client = new Client();
		
		Window.init();
		RenderMaster.init();
		ComponentMaster.init();
		
		KeyBoard.init();
		Mouse.init();
		
		Panel panel = new Panel(0, 0, 1000, 1+(int) (1000/Maths.getScreenRatio()));
		panel.setBackground(new Texture("bg"), 1);
		
		Button button = new Button("Beenden", 600, 800, 2, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		});
		button.setEffect(new OutlineEffect(new Color(200, 200, 200), 2));
		button.setColor(0, 0, 0);
		button.setBackground(new Texture("button"), 1);
		
		panel.add(button);
		panel.setVisible(true);
	}
	
	public static void update() {
		
		KeyBoard.update();
		Mouse.update();
		
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
