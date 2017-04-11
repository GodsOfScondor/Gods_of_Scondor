package scondor.panels;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.TextButton;
import scondor.image.Images;
import scondor.util.Action;
import scondor.util.Color;

public class PopUpPanel extends Panel {
	
	private static final int PRIORITY = 2;
	
	private TextButton button;
	private Label text;
	
	private Container container;
	
	public PopUpPanel() {
		super(Images.COLOR_BLACK, false);
		super.setReactVisibility(0.79f);
		
		/*
		 * create components
		 */
		
		button = new TextButton("NULL", 500, 800, 4, 0, new Action() {
			public void perform() {
				Containers.show(container);
				fade(0.8f, 0, 30);
			}
		}, true);
		text = new Label("NULL", 500, 650, 4, 0, true);
		
		/*
		 * add components to panel
		 */
		
		super.add(button);
		super.add(text);
		
		/*
		 * validate panel
		 */
		
		super.validate(PRIORITY);
		
	}
	
	public void popup(Container container, String popup, float r, float g, float b) {
		this.container = container;
		text.setColor(new Color(r, g, b));
		text.setText(popup);
		text.setCompX(500-text.getCompWidth()/2);
		System.out.println(button.getCompWidth());
		button.setCompX(500-(button.getCompWidth()/2));
		fade(0, 0.8f, 50);
	}

}
