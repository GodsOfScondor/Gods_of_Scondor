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
	private Button back;
	private int last;

	public PopUp() {
		super(1);
		setBackground(new Texture("bg"));
		
		label = new Label("null", 500,400,6,1).setEffect(new ShadowEffect(3)).setColor(1, 0, 0);
		back = new Button("BACK", 450, 650, 5, 1, new Action() {
			public void perform() {
				Panels.show(last);
			}
		}).setEffect(new GlowEffect(0, 0, 0, 2)).setDamper(0.2f).setColor(0.5f, 0.5f, 0.5f);
		
		add(label);
		add(back);
	}
	
	protected void setMSG(String msg, int last) {
		label.setText(msg);
		label.setXY((int) (520-((label.getWidth())/2f)), 400);
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
