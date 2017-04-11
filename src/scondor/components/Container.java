package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.image.Texture;
import scondor.util.Action;
import scondor.util.Slide;

public abstract class Container implements Fadeable {
	
	protected List<Component> comps;
	protected List<Action> actions;
	protected int priority;
	protected float visibility;
	protected Slide slide = null;
	
	public Container(int priority) {
		this.priority = priority;
		comps = new ArrayList<>();
		actions = new ArrayList<>();
	}
	
	public abstract void refresh();
	
	public void setBackground(Texture tex) {
		Picture pic = new Picture(tex, true);
		add(pic);
	}
	
	protected void add(Component comp) {
		comps.add(comp);
	}
	
	protected void remove(Component comp) {
		comps.remove(comp);
	}
	
	public void update() {
		
		for (Action action : actions)action.perform();
		
		if (slide != null) {
			visibility = slide.getValue()/1000f;
			if (slide.hasFinished()) {
				slide.destroy();
				slide = null;
			}
		}
		
		for (Action action : actions) action.perform();
		for (Component comp : comps) if (comp.isDepending()) comp.fade(visibility);
		if (visibility>0.99f) {
			refresh();
			for (Component comp : comps) comp.update();
		}
	}
	
	public void validate() {
		for (Component comp : comps) comp.validate(priority);
	}
	
	public void destroy() {
		for (Component comp : comps) comp.destroy();
		comps.clear();
	}
	
	@Override
	public void fade(float start, float end, int duration) {
		slide = new Slide((int)(start*1000), (int)(end*1000), duration);
		slide.run();
	}
	
}
