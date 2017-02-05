package scondor.components;

import scondor.util.Action;

public class Button extends Component {
	
	private boolean enabled;
	private Action action;
	private String text;
	
	public Button(String text, int x, int y, int width, int height, Action action) {
		super(x, y, width, height);
		enabled = true;
		this.action = action;
		this.text = text;
		
	}
	
	@Override
	protected void discard() {
		
	}

	@Override
	protected void showup() {
		
	}

	@Override
	protected void destroyComp() {
		
	}

	@Override
	protected void update() {
		
	}
	
}
