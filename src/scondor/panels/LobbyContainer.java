package scondor.panels;

import scondor.components.Container;
import scondor.components.TextButton;

public class LobbyContainer extends Container {

	private static final int PRIORITY = 1;
	private TextButton ranked, online, custom;

	public LobbyContainer() {
		super(PRIORITY);
		
		ranked = new TextButton("RANKED", 300, 300, 3, 1, null, true);
		online = new TextButton("ONLINE", 300, 300, 3, 1, null, true);
		custom = new TextButton("CUSTOM", 300, 300, 3, 1, null, true);
		
	}

	@Override
	public void refresh() {
		
	}

}
