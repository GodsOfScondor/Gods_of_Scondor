package scondor.components;

import java.util.ArrayList;
import java.util.List;

import scondor.image.Texture;
import scondor.inputs.KeyBoard;
import scondor.util.Slide;

public abstract class Container implements Fadeable {
	
	protected List<Component> comps;
	protected int priority;
	protected float visibility;
	protected Slide slide = null;
	
	private boolean open, before;
	
	public Container(int priority) {
		this.priority = priority;
		comps = new ArrayList<>();
	}
	
	public abstract void refresh();
	public abstract int getID();
	
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
		
		/*
		 * handle textfield mechanics (TAB function)
		 */
		matchTextField(false);
		
		/*
		 * update visibility 
		 */
		if (slide != null) {
			visibility = slide.getValue()/1000f;
			if (slide.hasFinished()) {
				slide.destroy();
				slide = null;
			}
		}
		
		/*
		 * trigger special function methods
		 */
		before = open;
		open = (visibility>0.99f);
		if (!before&&open) { showup(); matchTextField(true); }
		if (before&&!open) discard();
		if (!open) runInBackGround();
		
		/*
		 * update if container is visible
		 */
		for (Component comp : comps) if (comp.isDepending()) comp.fade(visibility);
		if (open) {
			refresh();
			for (Component comp : comps) comp.update();
		}
	}
	
	/*
	 * validates container
	 */
	public void validate() {
		for (Component comp : comps) comp.validate(priority);
	}
	
	/*
	 * destroys contaier
	 */
	public void destroy() {
		for (Component comp : comps) comp.destroy();
		comps.clear();
	}
	
	@Override
	public void fade(float start, float end, int duration) {
		slide = new Slide((int)(start*1000), (int)(end*1000), duration);
		slide.run();
	}
	
	/*
	 * special funtion methods can be overriden if needed
	 */
	public void runInBackGround() {}
	public void showup() {}
	public void discard() {}
	
	/*
	 * textfield mechanics
	 */
	public void matchTextField(boolean auto) {
		if (Containers.isOpen(this)) if (auto || KeyBoard.isKeyTyped(KeyBoard.KEY_TAB)) {
			
			TextField current = null;
			TextField next = null;
			TextField first = null;
			
			for (Component comp : comps) {
				if (comp instanceof TextField) {
					if (((TextField)comp).isFocused()) {
						current = (TextField) comp;
						break;
					}
				}
			}
			
			for (Component comp : comps) if (comp instanceof TextField) {
				if (current==comp) {
					((TextField)comp).setFocus(false);
					continue;
				}
				if (current!=null) {
					if (comp.getCompY()>=current.getCompY()) {
						if (next!=null) {
							if (comp.getCompY() < next.getCompY()) {
								next = (TextField) comp;
							}
						} else {
							next = (TextField) comp;
						}
					}
				} else {
					current = (TextField) comp;
					next = (TextField) comp;
				}
			}
			
			if (next!=null) next.setFocus(true);
			else {
				for (Component comp : comps) if (comp instanceof TextField) {
					if (first!=null) {
						if (comp.getCompY() < first.getCompY()) first = (TextField) comp;
					} else {
						first = (TextField) comp;
					}
				}
				if (first!=null) first.setFocus(true);
			}
			
		}
	}
	
}
