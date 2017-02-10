package scondor;

import scondor.components.Button;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.font.effect.GlowEffect;
import scondor.font.effect.OutlineEffect;
import scondor.font.effect.ShadowEffect;
import scondor.image.Texture;
import scondor.util.Action;
import scondor.util.Maths;

public class Main extends Panel {

	public Main() {
		/*
		 * create panel
		 */
		super(0, 0, 1000, 1 + (int) (1000/Maths.getScreenRatio()),1);
		setBackground(new Texture("bg"));
		
		/*
		 * create buttons
		 */
		Button button_exit = new Button("BEENDEN", 600, 800, 2, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f).fade(0, 1, 100);
		
		Button button_start = new Button("START", 600, 600, 2, 1, new Action() {
			@Override
			public void perform() {
				System.exit(0);
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f).fade(0, 1, 100);
		
		Button button_continue = new Button("FORTSETZEN", 600, 700, 2, 1, new Action() {
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
		add(button_start);
		add(button_continue);
		
		/*
		 * show panel
		 */
		show();
	}
	
}
