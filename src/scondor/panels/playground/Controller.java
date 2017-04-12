package scondor.panels.playground;

import scondor.components.Container;
import scondor.components.Label;

public class Controller extends Container {

	private static final int PRIORITY = 2;
	private static final int TIME = 10;
	
	private Label switch_msg, timer;
	private long time;
	
	public Controller() {
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		switch_msg = new Label("", 0, 400, 6, 2, false);
		timer = new Label("", 960, 900, 4, 3, false);
		
		/*
		 * add components to container
		 */
		
		super.add(switch_msg);
		super.add(timer);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}
	
	public void showMSG(String msg) {
		
		time = (TIME+1)*50;
		switch_msg.setText(msg);
		switch_msg.setCompX(500-(switch_msg.getCompWidth()/2));
		switch_msg.fade(1, 0, 100);
		timer.fade(0, 1, 1);
		
	}

	@Override
	public void refresh() {
		
		time -= 1;
		if (time%50==0) {
			timer.setText("" + Math.max(0, (time/50)));
			timer.setCompX(960-(timer.getCompWidth()/2));
		}
		
	}

	@Override
	public int getID() {
		return -1;
	}

}
