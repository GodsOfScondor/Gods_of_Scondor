package scondor.panels.shop;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.image.Images;

public class Shop extends Container {

	private static final int PRIORITY = 1;

	public Shop() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		super.setBackground(Images.WALLPAPER_SHOP);
		
		/*
		 * create effects and colors
		 */
		
		/*
		 * create components
		 */
		
		/*
		 * add components to container
		 */
		
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
		return Containers.SHOP;
	}
	
	
	
}
