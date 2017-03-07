package scondor.panels;

import scondor.GodsOfScondor;
import scondor.components.Button;
import scondor.components.Card;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.util.Action;

public class Shop extends Panel {

	private Label title;
	private Button shop, exit;
	private OutlineEffect effect;

	public Shop() {
		super(1);
		setBackground(new Texture("shop"));

		effect = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		title = new Label("ONLY TODAY: FREE CARDS FOR EVERYONE!!! ...just 9,99$/Card", 50, 50, 5, 1).setEffect(effect);

		shop = new Button("DECKS", 180, 500, 5, 1, new Action() {
			public void perform() {
			}
		}).setEffect(effect).setDamper(0.2f);
		exit = new Button("EXIT", 815, 600, 5, 1, new Action() {
			public void perform() {
				GodsOfScondor.close();
				;
			}
		}).setEffect(effect).setDamper(0.2f);

		addAction(new Action() {
			public void perform() {
			}
		});

		Card card1 = new Card(300, 370, 0.5f);
		Card card2 = new Card(325, 371, 0.5f);
		Card card3 = new Card(350, 372, 0.5f);
		Card card4 = new Card(375, 372, 0.5f);
		Card card5 = new Card(400, 371, 0.5f);
		Card card6 = new Card(425, 371, 0.5f);
		Card card7 = new Card(450, 370, 4f);

		add(card1);
		add(card2);
		add(card3);
		add(card4);
		add(card5);
		add(card6);
		add(card7);

		add(title);
		add(shop);
		add(exit);

	}

	@Override
	public void swipeIn() {
		fade(0, 1, Panels.FADEIN);
		for (Component comp : comps)
			if (comp instanceof EffectAble<?>)
				((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
	}

	@Override
	public void swipeOut() {
		fade(1, 0, Panels.FADEOUT);
		for (Component comp : comps)
			if (comp instanceof EffectAble<?>)
				((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);

	}

}
