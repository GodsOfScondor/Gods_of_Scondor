package scondor.panels;

import scondor.components.Button;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.font.effect.GlowEffect;
import scondor.font.effect.ShadowEffect;
import scondor.image.Texture;
import scondor.util.Action;

public class PopUp extends Panel {

	private Label label;
	private Button button;
	private int last;
	

	public PopUp() {
		super(1);
		setBackground(new Texture("lobby"));
		
		label = new Label("null", 500,400,6,1).setEffect(new ShadowEffect(3)).setColor(1, 0, 0);
		button = new Button("BACK", 450, 650, 5, 1, new Action() {
			public void perform() {
				Panels.show(last);
			}
		}).setEffect(new GlowEffect(0, 0, 0, 2)).setDamper(0.2f).setColor(0.5f, 0.5f, 0.5f);
		
		add(label);
		add(button);
	}
	
	protected void setMSG(String msg, String popup, float r, float g, float b, int last) {
		label.setText(popup);
		label.setColor(r, g, b);
		label.setXY((int) (520-((label.getWidth())/2f)), 400);
		button.setText(msg);
		this.last = last;
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
