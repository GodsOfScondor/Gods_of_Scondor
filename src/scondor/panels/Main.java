package scondor.panels;

import scondor.components.Button;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.font.effect.GlowEffect;
import scondor.image.Texture;
import scondor.util.Action;

public class Main extends Panel {

	private Button button_battle;
	private Button button_options;
	private Button button_exit;
	
	private Picture arrow;
	private Label label;

	public Main() {
		/*
		 * create panel
		 */
		super(1);
		setBackground(new Texture("bg"));
		
		/*
		 * create picture
		 */
		arrow = new Picture(new Texture("arrow"), 580, 400, 20, 20);
		
		/*
		 * create buttons
		 */
		button_battle = new Button("BATLINGER", 600, 500, 3, 1, new Action() {
			@Override
			public void perform() {
				
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		button_options = new Button("OPTIONS", 600, 600, 3, 1, new Action() {
			@Override
			public void perform() {
				Panels.popup("Time: " + System.currentTimeMillis());
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		button_exit = new Button("EXIT", 600, 700, 3, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		/*
		 * create labels
		 */
		label = new Label("THOMAPHEN v2.0", 150, 100, 5, 0).setEffect(new GlowEffect(0,0,0,5)).setColor(0.5f, 0.5f, 0.05f);
		
		/*
		 * create checkbox
		 */
		/*
		 * add actions to panel 
		 */
		addAction(new Action() {
			@Override
			public void perform() {
				if (button_battle.isTargeted()) arrow.setY(510);
				else if (button_options.isTargeted()) arrow.setY(610);
				else if (button_exit.isTargeted()) arrow.setY(710);
				else arrow.setY(1000);
			}
		});
		
		/*
		 * add comps to panel
		 */
		add(label);
		add(button_exit);
		add(button_battle);
		add(button_options);
		add(arrow);
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
