package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.util.Action;

public abstract class Component {
	
	protected List<Component> comps;
	protected List<Action> actions;
	protected int x,y,width,height;
	private boolean visible;
	
	public Component(int x,int y,int width,int height) {
		comps = new ArrayList<>();
		actions = new ArrayList<>();
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		ComponentMaster.comps.add(this);
		setVisible(false);
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		for (Component comp : comps) {
			comp.setVisible(visible);
		}
		if (visible) showup();
		else discard();
	}
	
	protected abstract void discard();
	protected abstract void showup();
	
	public void destroy() {
		for (Component comp : comps) {
			comp.destroy();
		}
		
		destroyComp();
		
		try { finalize();
		} catch (Throwable e) { e.printStackTrace(); }
	}
	
	protected abstract void destroyComp();
	
	public void add(Component comp) {
		comps.add(comp);
		setVisible(visible);
	}
	
	public void remove(Component comp) {
		comps.remove(comp);
		comp.setVisible(false);
		comp.destroy();
		setVisible(visible);
	}

	protected abstract void refresh();
	protected abstract void setPriority(int priority);
	
	public void validate(int priority) {
		setPriority(priority);
		for (Component comp : comps) {
			comp.validate(priority);
		}
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void addAction(Action action) {
		actions.add(action);
	}
	
	public void update() {
		for (Action action : actions)action.perform();
		refresh();
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
	
}
