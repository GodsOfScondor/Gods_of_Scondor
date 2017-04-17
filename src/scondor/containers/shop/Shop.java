package scondor.containers.shop;

import java.util.List;
import java.util.Random;

import scondor.components.Card;
import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Label;
import scondor.components.TextButton;
import scondor.deck.card.CardData;
import scondor.deck.card.troops.TroopCardData;
import scondor.font.effect.GlowEffect;
import scondor.font.effect.OutlineEffect;
import scondor.font.effect.ShadowEffect;
import scondor.image.Images;
import scondor.inputs.Mouse;
import scondor.mana.ManaType;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;

public class Shop extends Container {

	private static final int PRIORITY = 1;

	private boolean shopButtonsActive = true;

	private OutlineEffect outline;
	private GlowEffect glow;
	private ShadowEffect shadow;

	private Label title;
	private Label money;

	private TextButton shop, exit;

	private Card preview;
	private ProductShowcase showcase;

	private PackType type = null;

	public Shop() {
		super(PRIORITY);

		/*
		 * set background
		 */
		super.setBackground(Images.WALLPAPER_SHOP);

		/*
		 * status requests
		 */
		Client.sendToServer(new Message("money"));

		/*
		 * create effects and colors
		 */
		outline = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
		glow = new GlowEffect(0.5f, 0.5f, 0.5f, 0.5f);
		shadow = new ShadowEffect(0.5f, 0.5f, 0.5f, 0.5f);

		/*
		 * create components
		 */
		title = new Label("ONLY TODAY: FREE CARDS FOR EVERYONE!!! ...just 9,99$/Card", 50, 50, 5, 1, true)
				.setEffect(outline).setEffect(glow).setEffect(shadow);
		money = new Label("", 50, 950, 2, 2, true).setEffect(outline).setEffect(glow).setEffect(shadow);

		shop = new TextButton("BUY", 510, 500, 5.0f, 1, new Action() {
			public void perform() {
				if (type != null && shopButtonsActive) {
					ShopHandler.buy(type);
				}
			}
		}, true).setEffect(outline).setEffect(glow).setEffect(shadow).setDamper(0.2f);

		exit = new TextButton("BACK", 810, 600, 5, 1, new Action() {
			public void perform() {
				if (shopButtonsActive) {
					Containers.show(Containers.getMain());
				}
			}
		}, true).setEffect(outline).setEffect(glow).setEffect(shadow).setDamper(0.2f);

		Packs pack1 = new Packs(PackType.I_CLOSED, null, 300, 370, 25, 40);
		Packs.allPacks.add(pack1);

		Packs pack2 = new Packs(PackType.I_OPEN, null, 350, 370, 25, 40);
		Packs.allPacks.add(pack2);

		Packs pack3 = new Packs(PackType.X_CLOSED, null, 400, 370, 25, 40);
		Packs.allPacks.add(pack3);

		preview = new Card(new TroopCardData(0, "EXAMPLE", "AAAAAAAAAAAAAAAAAAA", 1, ManaType.WILD, 1, 2, 3), 50,
				500, 3, false);

		/*
		 * showcase stuff
		 */

		showcase = new ProductShowcase();
		showcase.fade(0, 1, 0);

		/*
		 * add components to container
		 */

		for (Packs pack : Packs.allPacks)
			super.add(pack.getPicture());

		add(preview);
		add(title);
		add(money);
		add(shop);
		add(exit);

		/*
		 * validate container
		 */
		super.validate();

	}

	@Override
	public void refresh() {
		money.setText("Your current money: " + ShopHandler.getMoney());
		for (Packs deck : Packs.allPacks) {
			if (deck.getPicture().isMouseOver()) {
				if (Mouse.isButtonTyped(0)) {

					TroopCardData data = new TroopCardData(new Random().nextInt(10), "", "", 0, ManaType.WILD, 0, 0, 0);
					preview.changeData(data);
					type = deck.getPackType();
				}
			}
		}
		showcase.update();
	}

	@Override
	public int getID() {
		return Containers.SHOP;
	}

	@Override
	public void showup() {
		preview.fade(0, 1, 0);
	}

	@Override
	public void discard() {
		preview.fade(1, 0, 0);
	}

	public void incomingData(List<CardData> cards, PackType type) {
		shopButtonsActive = false;
		showcase.incomingData(cards, type);
	}

	public boolean getShopButtonsActive() {
		return shopButtonsActive;
	}

	public void setShopButtonsActive(boolean shopButtonsActive) {
		this.shopButtonsActive = shopButtonsActive;
	}
}
