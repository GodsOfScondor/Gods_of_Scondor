package scondor.containers;

import scondor.GodsOfScondor;
import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.Picture;
import scondor.components.TextButton;
import scondor.font.effect.GlowEffect;
import scondor.image.Images;
import scondor.util.Action;
import scondor.util.Color;

public class Main extends Container {
	
	private static final int PRIORITY = 1;
	private TextButton button_battle;
	private TextButton button_shop;
	private TextButton button_deck;
	private TextButton button_options;
	private TextButton button_exit;
	
	private Picture arrow;
	private Label titel;
	
	public Main() {
		super(PRIORITY);
		
		/*
		 * set background
		 */
		
		super.setBackground(Images.WALLPAPER_MAIN);
		
		/*
		 * create effects and colors
		 */
		
		GlowEffect glow_effect = new GlowEffect(0,0,0, 3);
		Color gray = new Color(0.8f, 0.8f, 0.8f);
		Color yellow = new Color(0.5f, 0.5f, 0.05f);
		
		/*
		 * create buttons
		 */
		
		button_battle = new TextButton("PLAY", 800, 300, 3, 1, new Action() {
			public void perform() {
				Containers.show(Containers.getDeckChooser());
			}
		}, true).setEffect(glow_effect).setColor(gray);
		
		button_shop = new TextButton("SHOP", 800, 400, 3, 1, new Action() {
			public void perform() {
				Containers.show(Containers.getShop());
			}
		}, true).setEffect(glow_effect).setColor(gray);
		
		
		button_deck = new TextButton("DECK", 800, 500, 3, 1, new Action() {
			public void perform() {
				Containers.show(Containers.getDeckStarter());
			}
		}, true).setEffect(glow_effect).setColor(gray);
		
		button_options = new TextButton("OPTIONS", 800, 600, 3, 1, null, true).setEffect(glow_effect).setColor(gray);
		
		button_exit = new TextButton("EXIT", 800, 700, 3, 1, new Action() {
			public void perform() {
				GodsOfScondor.close();
			}
		}, true).setEffect(glow_effect).setColor(gray);
		
		/*
		 * create labels 
		 */
		
		titel = new Label("Gods of Scondor", 150, 100, 8, 1, true).setEffect(glow_effect).setColor(yellow);
		
		
		/*
		 * create pictures
		 */
		
		arrow = new Picture(Images.ICON_ARROW, 780, 400, 20, 20, false);
		
		/*
		 * add components to container
		 */
		
		super.add(button_battle);
		super.add(button_shop);
		super.add(button_deck);
		super.add(button_options);
		super.add(button_exit);
		super.add(titel);
		super.add(arrow);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}

	@Override
	public void refresh() {
		if (button_battle.isMouseOver()) arrow.setCompY(308);
		else if (button_shop.isMouseOver()) arrow.setCompY(408);
		else if (button_deck.isMouseOver()) arrow.setCompY(508);
		else if (button_options.isMouseOver()) arrow.setCompY(608);
		else if (button_exit.isMouseOver()) arrow.setCompY(708);
		else arrow.setCompY(1000);
	}
	
	@Override
	protected void showup() {
		arrow.fade(0, 1, 0);
	}
	
	@Override
	protected void discard() {
		arrow.fade(1, 0, 0);
	}

	@Override
	public int getID() {
		return Containers.MAIN;
	}
	
}
