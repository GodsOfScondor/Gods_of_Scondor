package scondor.containers.battlefield;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.IconButton;
import scondor.components.Picture;
import scondor.components.TextButton;
import scondor.containers.battlefield.endofgame.EndOfGameType;
import scondor.image.Images;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;

public class BattleMenu extends Container {

	private static final int PRIORITY = 4;
	
	private IconButton menu_button;
	private TextButton resume;
	private TextButton settings;
	private TextButton surrender;
	private Picture black_fade;
	private boolean open;
	
	public BattleMenu() {
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		menu_button = new IconButton(Images.ICON_MENU, 10, 10, 60, 60, new Action() {
			public void perform() {
				show();
			}
		}, true);
		resume = new TextButton("RESUME GAME", 500, 300, 4f, 3, new Action() {
			public void perform() {
				if (open) show();
			}
		}, false);
		resume.setCompX(500-(resume.getCompWidth()/2));
		settings = new TextButton("SETTINGS", 500, 400, 4f, 3, null, false);
		settings.setCompX(500-(settings.getCompWidth()/2));
		surrender = new TextButton("SURRENDER", 500, 500, 4f, 3, new Action() {
			public void perform() {
				Client.sendToServer(new Message("fight;action;surrender"));
				Containers.getBattlefield().end(EndOfGameType.SURRENDER_LOSE);
			}
		}, false);
		surrender.setCompX(500-(surrender.getCompWidth()/2));
		black_fade = new Picture(Images.COLOR_BLACK, false);
		
		/*
		 * add components to container
		 */
		
		super.add(menu_button);
		super.add(resume);
		super.add(settings);
		super.add(surrender);
		super.add(black_fade);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}
	
	private void show() {
		open = !open;
		
		if (open) {
			Containers.getBattlefield().getPlayerhand().discardCardPreview();
			resume.fade(0, 1, 30);
			surrender.fade(0, 1, 30);
			settings.fade(0, 1, 30);
			black_fade.fade(0, 0.3f, 30);
		} else {
			resume.fade(1, 0, 30);
			surrender.fade(1, 0, 30);
			settings.fade(1, 0, 30);
			black_fade.fade(0.3f, 0, 30);
		}
	}
	
	public boolean isOpen() {
		return open;
	}
	
	@Override
	public void showup() {
		black_fade.fade(1, 0, 0);
	}
	
	@Override
	public void discard() {
		menu_button.fade(1, 0, 0);
		resume.fade(1, 0, 0);
		settings.fade(1, 0, 0);
		surrender.fade(1, 0, 0);
	}
	
	@Override
	public void refresh() {
		
	}

	@Override
	public int getID() {
		return 0;
	}
	
	
	
}
