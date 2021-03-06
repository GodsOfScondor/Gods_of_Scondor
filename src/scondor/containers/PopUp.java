package scondor.containers;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.Picture;
import scondor.components.TextButton;
import scondor.font.effect.OutlineEffect;
import scondor.image.Images;
import scondor.util.Action;
import scondor.util.Color;

public class PopUp extends Container {
	
	private static final int PRIORITY = 2;
	
	private TextButton button;
	private Label text;
	private Picture background;
	private Container container;
	
	public PopUp() {
		
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		background = new Picture(Images.COLOR_BLACK, false);
		button = new TextButton("NULL", 500, 500, 4, 0, new Action() {
			public void perform() {
				if (container!=null) Containers.show(container);
				fade(1, 0, 30);
				background.fade(0.9f, 0, 30);
			}
		}, true).setEffect(new OutlineEffect(1, 1, 1, 3));
		text = new Label("NULL", 500, 350, 4, 0, true).setEffect(new OutlineEffect(1, 1, 1, 3));
		
		/*
		 * add components to container
		 */
		
		super.add(background);
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
		fade(0, 1f, 50);
		background.fade(0, 0.9f, 50);
		
		text.setColor(new Color(r, g, b));
		text.setText(msg);
		text.setCompX(500-(text.getCompWidth()/2));
		
		if (this.container==null) button.setText("BACK");
		else button.setText("NEXT");
		button.setCompX(500-(button.getCompWidth()/2));
		
	}

}
