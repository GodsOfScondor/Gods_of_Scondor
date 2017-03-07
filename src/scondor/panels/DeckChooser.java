package scondor.panels;

import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.image.Texture;

public class DeckChooser extends Panel {

	private Label text;
	
	public DeckChooser() {
		super(1);
		super.setBackground(new Texture("lobby"));
		text = new Label("Choose your deck: ", 100, 100, 5, 1);
		
		add(text);
	}

	@Override
	public void swipeIn() {
		fade(0, 1, Panels.FADEIN);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
	}

	@Override
	public void swipeOut() {
		fade(1, 0, Panels.FADEOUT);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
	}
	
}
