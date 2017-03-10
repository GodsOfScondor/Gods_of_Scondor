package scondor.panels;

import scondor.GodsOfScondor;
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
	private Button button_shop;
	private Button button_deck;
	private Button button_options;
	private Button button_exit;
	
	private Picture arrow;
	private Label label;

	public Main() {
		/*
		 * create panel
		 */
		super(1);
		setBackground(new Texture("lobby"));
		
		/*
		 * create picture
		 */
		arrow = new Picture(new Texture("arrow"), 580, 400, 20, 20);
		
		/*
		 * create buttons
		 */
		button_battle = new Button("PLAY", 600, 300, 3, 1, new Action() {
			public void perform() {
				Panels.show(Panels.LOBBY);
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		button_shop = new Button("SHOP", 600, 400, 3, 1, new Action() {
			public void perform() {
				Panels.show(Panels.SHOP);
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		button_deck = new Button("DECK", 600, 500, 3, 1, new Action() {
			public void perform() {
				
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		button_options = new Button("OPTIONS", 600, 600, 3, 1, new Action() {
			public void perform() {
				
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		button_exit = new Button("EXIT", 600, 700, 3, 1, new Action() {
			public void perform() {
				GodsOfScondor.close();
			}
		}).setEffect(new GlowEffect(0,0,0, 3)).setColor(0.5f, 0.5f, 0.5f);
		
		/*
		 * create labels
		 */
		label = new Label("Gods of Scondor", 150, 100, 5, 0).setEffect(new GlowEffect(0,0,0,3)).setColor(0.5f, 0.5f, 0.05f);
		
		/*
		 * create checkbox
		 */
		/*
		 * add actions to panel 
		 */
		addAction(new Action() {
			public void perform() {
				if (button_battle.isTargeted()) arrow.setY(310);
				else if (button_shop.isTargeted()) arrow.setY(410);
				else if (button_deck.isTargeted()) arrow.setY(510);
				else if (button_options.isTargeted()) arrow.setY(610);
				else if (button_exit.isTargeted()) arrow.setY(710);
				else arrow.setY(1000);
			}
		});
		
		/*b
		 * add comps to panel
		 */
		add(label);
		add(button_exit);
		add(button_battle);
		add(button_options);
		add(button_shop);
		add(button_deck);
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
