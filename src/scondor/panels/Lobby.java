package scondor.panels;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.TextButton;

public class Lobby extends Container {

	private static final int PRIORITY = 1;
	private TextButton ranked, online, custom;

	public Lobby() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		/*
		 * create effects and colors
		 */
		
		/*
		 * create components
		 */
		
		ranked = new TextButton("RANKED", 300, 300, 3, 1, null, true);
		online = new TextButton("ONLINE", 300, 300, 3, 1, null, true);
		custom = new TextButton("CUSTOM", 300, 300, 3, 1, null, true);
		
		/*
		 * add components to container
		 */
		
		super.add(ranked);
		super.add(online);
		super.add(custom);
		
		/*
		 * validate contianer
		 */
		
		super.validate();
		
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public int getID() {
		return Containers.LOBBY;
	}

}
