package scondor.containers.battlefield;

import scondor.components.Container;
import scondor.components.IconButton;
import scondor.components.Label;
import scondor.image.Images;

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
		switch_button = new IconButton(Images.REFRESH, 900, 860, 80, 80, null, true);
		
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
		
		time = (TIME+1)*50;
		switch_msg.setText(onturn?"Your turn!":"Enemys turn!");
		switch_msg.setCompX(500-(switch_msg.getCompWidth()/2));
		switch_button.setResize(onturn?1.2f:1f);
		switch_msg.fade(1, 0, 100);
		timer.fade(0, 1, 1);
		
	}

	@Override
	public void refresh() {
		
		time -= 1;
		if (time%50==0) {
			timer.setText("" + Math.max(0, (time/50)));
			timer.setCompX(940-(timer.getCompWidth()/2));
		}
		
	}

	@Override
	public int getID() {
		return -1;
	}

}
