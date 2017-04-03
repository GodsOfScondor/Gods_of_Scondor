package scondor.panels.shop;

import java.util.List;
import java.util.Random;

import scondor.components.Button;
import scondor.components.Card;
import scondor.components.Component;
import scondor.components.Label;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.deck.card.CardData;
import scondor.deck.card.troops.TroopCardData;
import scondor.font.effect.GlowEffect;
import scondor.font.effect.OutlineEffect;
import scondor.font.effect.ShadowEffect;
import scondor.image.Texture;
import scondor.inputs.Mouse;
import scondor.mana.ManaType;
import scondor.packets.Message;
import scondor.panels.EffectAble;
import scondor.panels.Panels;
import scondor.server.Client;
import scondor.util.Action;
import scondor.util.Maths;

public class Shop extends Panel {

	private Label title;
	private Label money;
	private Button shop, exit;
	private OutlineEffect outline;
	private GlowEffect glow;
	private ShadowEffect shadow;
	private Card preview;
	private Card postview;
	private PackType type = null;
	private Picture blackfade;

	public Shop() {
		super(1);
		setBackground(new Texture("shop"));
		Client.sendToServer(new Message("money"));

		outline = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		glow = new GlowEffect(0.5f, 0.5f, 0.5f, 0.5f);
		shadow = new ShadowEffect(0.5f, 0.5f, 0.5f, 0.5f);

		title = new Label("ONLY TODAY: FREE CARDS FOR EVERYONE!!! ...just 9,99$/Card", 50, 50, 5, 1).setEffect(outline)
				.setEffect(glow).setEffect(shadow);
		money = new Label("", 50, 950, 2, 2).setEffect(outline).setEffect(glow).setEffect(shadow);

		shop = new Button("BUY", 510, 500, 5, 1, new Action() {
			public void perform() {
				if (type != null) {
					ShopHandler.buy(type);
				}
			}
		}).setEffect(outline).setEffect(glow).setEffect(shadow).setDamper(0.2f);
		exit = new Button("BACK", 810, 600, 5, 1, new Action() {
			public void perform() {
				Panels.show(Panels.MAIN);
			}
		}).setEffect(outline).setEffect(glow).setEffect(shadow).setDamper(0.2f);

		addAction(new Action() {
			public void perform() {
			}
		});

		addAction(new Action() {
			public void perform() {
				if (isVisible()) {
					money.setText("Your current money: " + ShopHandler.getMoney());
					for (Packs deck : Packs.allPacks) {
						if (deck.getPicture().isMouseOver()) {
							deck.getPicture().setWidth(30);
							deck.getPicture().setHeight(48);
							if (Mouse.isButtonTyped(0)) {

								TroopCardData data = new TroopCardData(new Random().nextInt(10), "", "", 0,
										ManaType.WILD, 0, 0, 0);
								preview.setData(data);
								type = deck.getPackType();
							}
						} else {
							deck.getPicture().setWidth(25);
							deck.getPicture().setHeight(40);
						}
					}
					if (preview.hasData()) {
						// deckPreview = picBuffer;
						preview.setVisible(true);

					} else {
						preview.setVisible(false);
					}
				}
			}
		});

		Packs pack1 = new Packs(PackType.I_CLOSED, null, 300, 370, 25, 40);
		Packs.allPacks.add(pack1);

		Packs pack2 = new Packs(PackType.I_OPEN, null, 330, 370, 25, 40);
		Packs.allPacks.add(pack2);

		for (Packs pack : Packs.allPacks) {
			add(pack.getPicture());
		}

		preview = new Card(null, 50, 550, 3);
		postview = new Card(null, 50, 50, 5).setLayer(0.2f);

		blackfade = new Picture(new Texture("colors/black"), 0, 0, 1000, 1 + (int) (1000 / Maths.getScreenRatio()));
		blackfade.setLayer(0.3f);// set auf kleiner um picture vor darkscreen zu
									// bringen

		add(blackfade);
		add(preview);
		add(postview);
		add(title);
		add(money);
		add(shop);
		add(exit);
	}

	@Override
	public void swipeIn() {
		fade(0, 1, Panels.FADEIN);

		for (Component comp : comps)
			if (comp instanceof EffectAble<?>)
				((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);

		this.blackfade.stop();
		this.blackfade.setVisible(false);
		this.postview.stop();
		this.postview.setVisible(false);
	}

	@Override
	public void swipeOut() {
		fade(1, 0, Panels.FADEOUT);
		for (Component comp : comps)
			if (comp instanceof EffectAble<?>)
				((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);

	}

	protected void showData(List<CardData> cards, PackType type) {
		this.blackfade.fade(0.0f, 0.6f, 200);

		switch (type.toString()) {
		case "I_CLOSED":
			postview.setData(cards.get(0));
			postview.fade(0.0f, 1f, 200);
			break;
		case "I_OPEN":

			break;
		case "X_CLOSED":

			break;
		}
	}
}
