package scondor.panels;

import java.util.ArrayList;

import scondor.GodsOfScondor;
import scondor.components.Button;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.util.Action;

public class Shop extends Panel {

	private Label title;
	private Button shop, exit;
	private OutlineEffect effect;
	private ArrayList<Picture> decks = new ArrayList<>();

	public Shop() {
		super(1);
		setBackground(new Texture("shop"));

		effect = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		title = new Label("ONLY TODAY: FREE CARDS FOR EVERYONE!!! ...just 9,99$/Card", 50, 50, 5, 1).setEffect(effect);

		shop = new Button("DECKS", 180, 500, 5, 1, new Action() {
			public void perform() {
				ShopHandler.buy(200);
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

		addAction(new Action() {
			public void perform() {
				for (Picture deck : decks) {
					if (Mouse.X >= deck.getX() && Mouse.X <= deck.getX() + deck.getWidth() && Mouse.Y >= deck.getY()
							&& Mouse.Y <= deck.getY() + deck.getHeight()) {
						deck.setWidth(30);
						deck.setHeight(48);
					} else {
						deck.setWidth(25);
						deck.setHeight(40);
					}
				}

			}
		});

		Picture deck1 = new Picture(new Texture("card_gn_common"), 300, 370, 25, 40);
		decks.add(deck1);
		Picture deck2 = new Picture(new Texture("card_gn_common"), 325, 372, 25, 40);
		decks.add(deck2);
		Picture deck3 = new Picture(new Texture("card_gn_common"), 350, 372, 25, 40);
		decks.add(deck3);
		Picture deck4 = new Picture(new Texture("card_gn_common"), 375, 372, 25, 40);
		decks.add(deck4);
		Picture deck5 = new Picture(new Texture("card_gn_common"), 400, 372, 25, 40);
		decks.add(deck5);
		Picture deck6 = new Picture(new Texture("card_gn_common"), 425, 372, 25, 40);
		decks.add(deck6);
		Picture deck7 = new Picture(new Texture("card_gn_common"), 450, 372, 25, 40);
		decks.add(deck7);

		for (Picture deck : decks) {
			add(deck);
		}

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
