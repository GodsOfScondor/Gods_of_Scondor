package scondor.components;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {
	
	protected List<Component> comps;
	protected int x,y,width,height;
	private boolean visible;
	
	public Component(int x,int y,int width,int height) {
		comps = new ArrayList<>();
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
	}
	
	public void remove(Component comp) {
		comps.remove(comp);
	}

	protected abstract void update();
	
	public boolean isVisible() {
		return visible;
	}
	
}
