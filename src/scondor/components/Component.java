package scondor.components;

import java.util.ArrayList;
import java.util.List;

public abstract class Component {
	
	protected List<Component> comps;
	protected int x,y,width,height;
	
	public Component() {
		comps = new ArrayList<>();
		this.x=0;
		this.y=0;
		this.width=0;
		this.height=0;
	}
	
	public Component(int x,int y,int width,int height) {
		comps = new ArrayList<>();
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public void setVisible(boolean visible) {
		for (Component comp : comps) {
			comp.setVisible(visible);
		}
		discard();
	}
	
	protected abstract void discard();
	
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
	
}
