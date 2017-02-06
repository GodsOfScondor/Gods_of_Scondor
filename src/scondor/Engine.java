package scondor;

import scondor.components.Button;
import scondor.components.ComponentMaster;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.font.OutlineEffect;
import scondor.image.Texture;
import scondor.inputs.KeyBoard;
import scondor.inputs.Mouse;
import scondor.render.RenderMaster;
import scondor.server.Client;
import scondor.util.Action;
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
		
		Panel panel = new Panel(0, 0, 1000, 1 + (int) (1000/Maths.getScreenRatio()),1).setBackground(new Texture("bg"));
		
		Button button_exit = new Button("BEENDEN", 600, 800, 2, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		}).setEffect(new OutlineEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		Button button_start = new Button("START", 600, 600, 2, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		}).setEffect(new OutlineEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		Button button_continue = new Button("FORTSETZTEN", 600, 700, 2, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		}).setEffect(new OutlineEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		Label label = new Label("GODS OF SCONDOR", 150, 100, 5, 0).setEffect(new OutlineEffect(0,0,0, 3)).setColor(1, 1, 1);
		panel.add(label);
		
		panel.add(button_exit);
		panel.add(button_start);
		panel.add(button_continue);
		
		panel.show();
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
