package scondor.panels;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.TextButton;
import scondor.font.effect.OutlineEffect;
import scondor.image.Images;
import scondor.util.Action;
import scondor.util.Color;

public class PopUp extends Container {
	
	private static final int PRIORITY = 2;
	
	private TextButton button;
	private Label text;
	private Container container;
	
	public PopUp() {
		
		super(PRIORITY);
		
		super.setBackground(Images.COLOR_BLACK);
		
		/*
		 * create components
		 */
		
		button = new TextButton("NULL", 500, 500, 4, 0, new Action() {
			public void perform() {
				if (container!=null) Containers.show(container);
				fade(0.8f, 0, 30);
			}
		}, false).setEffect(new OutlineEffect(1, 1, 1, 3));
		text = new Label("NULL", 500, 350, 4, 0, false).setEffect(new OutlineEffect(0, 0, 0, 3));
		
		/*
		 * add components to container
		 */
		
		super.add(button);
		super.add(text);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public int getID() {
		return Containers.POPUP;
	}

	public void popup(Container container, String msg, float r, float g, float b) {
		
		this.container = container;
		fade(0, 0.9f, 50);
		text.fade(0, 1, 50);
		button.fade(0, 1, 50);
		
		text.setColor(new Color(r, g, b));
		text.setText(msg);
		text.setCompX(500-(text.getCompWidth()/2));
		
		if (this.container==null) button.setText("BACK");
		else button.setText("NEXT");
		button.setCompX(500-(button.getCompWidth()/2));
		
	}

}
