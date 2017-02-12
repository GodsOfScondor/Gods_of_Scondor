package scondor;

import scondor.components.Button;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.font.effect.GlowEffect;
import scondor.font.effect.ShadowEffect;
import scondor.image.Texture;
import scondor.util.Action;
import scondor.util.Maths;

public class Main extends Panel {

	private Button button_battle;
	private Button button_options;
	private Button button_exit;
	
	private Picture arrow;

	public Main() {
		/*
		 * create panel
		 */
		super(0, 0, 1000, 1 + (int) (1000/Maths.getScreenRatio()),1);
		setBackground(new Texture("bg"));
		
		/*
		 * create picture
		 */
		arrow = new Picture(new Texture("arrow"), 580, 400, 20, 35);
		
		/*
		 * create buttons
		 */
		button_battle = new Button("BATTLE", 600, 500, 3, 1, new Action() {
			@Override
			public void perform() {
				
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f).fade(0, 1, 100);
		
		addAction(new Action() {
			@Override
			public void perform() {
				if (button_battle.isTargeted()) arrow.setY(510);
				else if (button_options.isTargeted()) arrow.setY(610);
				else if (button_exit.isTargeted()) arrow.setY(710);
				else arrow.setY(1000);
			}
		});
		
		button_options = new Button("OPTIONS", 600, 600, 3, 1, new Action() {
			@Override
			public void perform() {
				setVisible(false);
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f).fade(0, 1, 100);
		
		button_exit = new Button("EXIT", 600, 700, 3, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f).fade(0, 1, 100);
		
		/*
		 * create labels
		 */
		Label label = new Label("GODS OF SCONDOR", 150, 100, 5, 0).setEffect(new ShadowEffect(0,0,0,5)).setColor(0.05f, 0.3f, 0.15f).fade(0, 1, 100).slideY(250, 100, 80);
		
		/*
		 * add comps to panel
		 */
		add(label);
		add(button_exit);
		add(button_battle);
		add(button_options);
		add(arrow);
		
		/*
		 * show panel
		 */
		show();
	}
	
}
