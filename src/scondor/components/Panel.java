package scondor.components;

import scondor.image.Texture;
import scondor.util.Action;
import scondor.util.Maths;

public class Panel extends Component {
	
	private ContainerPanel container;
	
	public Panel(Texture tex, boolean depending) {
		super(0, 0, 1000, (int) (1 + 1000*Maths.getScreenRatio()), depending);
		container = new ContainerPanel(tex , x, y, width, height, depending);
	}
	
	public Panel(int x, int y, int width, int height, Texture tex, boolean depending) {
		super(x, y, width, height, depending);
		container = new ContainerPanel(tex , x, y, width, height, depending);
	}
	
	public void setReactVisibility(float react_visibility) {
		container.setReactVisibility(react_visibility);
	}
	
	public void add(Component comp) {
		comp.setCompX(x+comp.getCompX());
		comp.setCompY(y+comp.getCompY());
		container.add(comp);
	}
	
	public void remove(Component comp) {
		container.remove(comp);
	}
	
	private static class ContainerPanel extends Container {
		
		private boolean depending;
		private float react_visibility;
		
		public ContainerPanel(Texture tex, int x, int y, int width, int height, boolean depending) {
			super(-1);
			if (tex!=null) super.add(new Picture(tex, x, y, width, height, true));
			this.depending = depending;
			this.react_visibility = 0.9f;
		}
		
		public void setReactVisibility(float react_visibility) {
			this.react_visibility = react_visibility;
		}

		@Override
		public void refresh() {}
		
		public void setPriority(int priority) {
			this.priority = priority;
		}
		
		@Override
		public void update() {
			for (Action action : actions) action.perform();
			if (slide != null) {
				visibility = slide.getValue()/1000f;
				if (slide.hasFinished()) {
					slide.destroy();
					slide = null;
				}
			}
			
			for (Action action : actions) action.perform();
			if (!depending) {
				for (Component comp : comps) if (comp.isDepending()) comp.fade(visibility);
			}
			if (super.visibility>react_visibility) {
				refresh();
				for (Component comp : comps) comp.update();
			}
		}
		
		public void fade(float visibility) {
			for (Component comp : comps) {
				if (comp.isDepending()) {
					comp.fade(visibility);
				}
			}
		}
		
	}
	
	public boolean isVisible() {
		return container.visibility>0.99f;
	}
	
	public boolean isInVisible() {
		return container.visibility<0.01f;
	}

	@Override
	public void fade(float start, float end, int duration) {
		if (!super.isDepending()) container.fade(start, end, duration);
	}

	@Override
	protected void destroy() {
		container.destroy();
	}

	@Override
	protected void update() {
		container.update();
	}

	@Override
	protected void fade(float visibility) {
		if (super.isDepending()) container.fade(visibility);
	}

	@Override
	protected void validate(int priority) {
		container.setPriority(priority);
		container.validate();
	}
	
}
