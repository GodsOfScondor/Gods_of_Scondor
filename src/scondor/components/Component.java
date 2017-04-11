package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.util.Action;

public abstract class Component implements Fadeable {
	
	private List<Action> actions;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	private boolean depending;
	
	public Component(int x,int y,int width,int height, boolean depending) {
		
		actions = new ArrayList<>();
		
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.depending = depending;
		
	}
	
	protected abstract void destroy();
	protected abstract void update();
	protected abstract void fade(float visibility);
	protected abstract void validate(int priority);
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public boolean isDepending() {
		return depending;
	}
	
	public int getCompX() {
		return x;
	}

	public int getCompY() {
		return y;
	}

	public int getCompWidth() {
		return width;
	}

	public int getCompHeight() {
		return height;
	}

	public void setCompX(int x) {
		this.x = x;
	}

	public void setCompY(int y) {
		this.y = y;
	}

	public void setCompWidth(int width) {
		this.width = width;
	}

	public void setCompHeight(int height) {
		this.height = height;
	}
	
	
	
}
