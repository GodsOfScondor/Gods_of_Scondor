package scondor.panels;

import java.util.ArrayList;
import java.util.Random;

import scondor.components.Button;
import scondor.components.Card;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.deck.card.troops.TroopCardData;
import scondor.font.effect.OutlineEffect;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.mana.ManaType;
import scondor.util.Action;

public class Shop extends Panel {

	private Label title;
	private Button shop, exit;
	private OutlineEffect effect;
	private ArrayList<Picture> decks = new ArrayList<>();
	private Card preview;
	
	public Shop() {
		super(1);
		setBackground(new Texture("shop"));

		effect = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		title = new Label("ONLY TODAY: FREE CARDS FOR EVERYONE!!! ...just 9,99$/Card", 50, 50, 5, 1).setEffect(effect);

		shop = new Button("BUY", 210, 500, 5, 1, new Action() {
			public void perform() {
				ShopHandler.buy(200);
				//@berni: Card component "cost" hinzufügen damit ShopHandler.buy(preview.getCost()); ???
			}
		}).setEffect(effect).setDamper(0.2f);
		exit = new Button("BACK", 810, 600, 5, 1, new Action() {
			public void perform() {
					Panels.show(Panels.MAIN);
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
					if (deck.isMouseOver()) {
						deck.setWidth(30);
						deck.setHeight(48);
						if(Mouse.isButtonTyped(0)){
							
							TroopCardData data = new TroopCardData(new Random().nextInt(10), "", "", 0, ManaType.WILD, 0, 0, 0);
							preview.setData(data);
						}
					} else {
						deck.setWidth(25);
						deck.setHeight(40);
					}
				}
				if(preview.hasData()) {
					//deckPreview = picBuffer;
					preview.setVisible(true);
					
				} else {
					preview.setVisible(false);
				}
			}
		});
		
		Texture card_border = new Texture("card_gn_common");
		
		Picture deck1 = new Picture(card_border, 300, 370, 25, 40);
		decks.add(deck1);
		Picture deck2 = new Picture(card_border, 325, 372, 25, 40);
		decks.add(deck2);
		Picture deck3 = new Picture(card_border, 350, 372, 25, 40);
		decks.add(deck3);
		Picture deck4 = new Picture(card_border, 375, 372, 25, 40);
		decks.add(deck4);
		Picture deck5 = new Picture(card_border, 400, 372, 25, 40);
		decks.add(deck5);
		Picture deck6 = new Picture(card_border, 425, 372, 25, 40);
		decks.add(deck6);
		Picture deck7 = new Picture(card_border, 450, 372, 25, 40);
		decks.add(deck7);
		for (Picture deck : decks) {
			add(deck);
		}

		preview = new Card(null, 50, 550, 3);
		
		add(preview);
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
