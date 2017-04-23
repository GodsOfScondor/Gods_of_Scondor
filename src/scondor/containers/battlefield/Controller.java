package scondor.containers.battlefield;

import scondor.components.Container;
import scondor.components.IconButton;
import scondor.components.Label;
import scondor.image.Images;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;
import scondor.util.Window;

public class Controller extends Container {

	private static final int PRIORITY = 2;
	private static final int TIME = 10;
	
	private Label switch_msg, timer;
	private IconButton switch_button;
	private long time;
	
	public Controller() {
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		switch_msg = new Label("", 0, 400, 6, 2, false);
		timer = new Label("", 940, 892, 3, 3, false);
		switch_button = new IconButton(Images.REFRESH, 900, 860, 80, 80, new Action() {
			@Override
			public void perform() {
				Client.sendToServer(new Message("fight;action;switch"));
			}
		}, true);
		
		/*
		 * add components to container
		 */
		
		super.add(switch_msg);
		super.add(timer);
		super.add(switch_button);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}
	
	public void turn(boolean onturn) {
		
		time = (TIME+1)*Window.FPS;
		switch_msg.setText(onturn?"Your turn!":"Enemys turn!");
		switch_msg.setCompX(500-(switch_msg.getCompWidth()/2));
		switch_button.setResize(onturn?1.2f:1f);
		switch_msg.fade(1, 0, 100);
		timer.fade(0, 1, 0);
		
	}
	
	@Override
	public void discard() {
		switch_msg.fade(1, 0, 0);
		switch_button.fade(1, 0, 0);
		timer.fade(1, 0, 0);
	}

	@Override
	public void refresh() {
		
		time -= 1;
		if (time%Window.FPS==0) {
			timer.setText("" + Math.max(0, (time/Window.FPS)));
			timer.setCompX(940-(timer.getCompWidth()/2));
		}
		
	}

	@Override
	public int getID() {
		return -1;
	}

}
