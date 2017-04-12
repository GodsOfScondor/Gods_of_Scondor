package scondor.panels.shop;
//package scondor.panels.shop;
//
//import java.util.List;
//import java.util.Random;
//
//import scondor.components.Button;
//import scondor.components.Card;
//import scondor.components.Component;
//import scondor.components.Label;
//import scondor.components.Panel;
//import scondor.deck.card.CardData;
//import scondor.deck.card.troops.TroopCardData;
//import scondor.font.effect.GlowEffect;
//import scondor.font.effect.OutlineEffect;
//import scondor.font.effect.ShadowEffect;
//import scondor.image.Texture;
//import scondor.inputs.Mouse;
//import scondor.mana.ManaType;
//import scondor.packets.Message;
//import scondor.panels.EffectAble;
//import scondor.panels.Panels;
//import scondor.server.Client;
//import scondor.util.Action;
//
//public class Shop extends Panel {
//
//	private Label title;
//	private Label money;
//	private Button shop, exit;
//	private OutlineEffect outline;
//	private GlowEffect glow;
//	private ShadowEffect shadow;
//	private Card preview;
//	private PackType type = null;
//	private ProductShowcase showcase;
//
//	public Shop() {
//		super(1);
//		setBackground(new Texture("shop"));
//		Client.sendToServer(new Message("money"));
//
//		outline = new OutlineEffect(0.5f, 0.5f, 0.5f, 0.8f);
//		glow = new GlowEffect(0.5f, 0.5f, 0.5f, 0.5f);
//		shadow = new ShadowEffect(0.5f, 0.5f, 0.5f, 0.5f);
//
//		title = new Label("ONLY TODAY: FREE CARDS FOR EVERYONE!!! ...just 9,99$/Card", 50, 50, 5, 1).setEffect(outline)
//				.setEffect(glow).setEffect(shadow);
//		money = new Label("", 50, 950, 2, 2).setEffect(outline).setEffect(glow).setEffect(shadow);
//
//		shop = new Button("BUY", 510, 500, 5, 1, new Action() {
//			public void perform() {
//				if (type != null) {
//					ShopHandler.buy(type);
//				}
//			}
//		}).setEffect(outline).setEffect(glow).setEffect(shadow).setDamper(0.2f);
//		exit = new Button("BACK", 810, 600, 5, 1, new Action() {
//			public void perform() {
//				Panels.show(Panels.MAIN);
//			}
//		}).setEffect(outline).setEffect(glow).setEffect(shadow).setDamper(0.2f);
//
//		addAction(new Action() {
//			public void perform() {
//			}
//		});
//
//		addAction(new Action() {
//			public void perform() {
//				if (isVisible()) {
//					money.setText("Your current money: " + ShopHandler.getMoney());
//					for (Packs deck : Packs.allPacks) {
//						if (deck.getPicture().isMouseOver()) {
//							deck.getPicture().setWidth(30);
//							deck.getPicture().setHeight(48);
//							if (Mouse.isButtonTyped(0)) {
//
//								TroopCardData data = new TroopCardData(new Random().nextInt(10), "", "", 0,
//										ManaType.WILD, 0, 0, 0);
//								preview.setData(data);
//								type = deck.getPackType();
//							}
//						} else {
//							deck.getPicture().setWidth(25);
//							deck.getPicture().setHeight(40);
//						}
//					}
//					if (preview.hasData()) {
//						// deckPreview = picBuffer;
//						preview.setVisible(true);
//
//					} else {
//						preview.setVisible(false);
//					}
//				}
//			}
//		});
//		
//		showcase = new ProductShowcase();
//		
//		Packs pack1 = new Packs(PackType.I_CLOSED, null, 300, 370, 25, 40);
//		Packs.allPacks.add(pack1);
//
//		Packs pack2 = new Packs(PackType.I_OPEN, null, 330, 370, 25, 40);
//		Packs.allPacks.add(pack2);
//
//		for (Packs pack : Packs.allPacks) {
//			add(pack.getPicture());
//		}
//
//		preview = new Card(null, 50, 550, 3);
//
//		add(showcase);
//		add(preview);
//		add(title);
//		add(money);
//		add(shop);
//		add(exit);
//	}
//
//	@Override
//	public void swipeIn() {
//		fade(0, 1, Panels.FADEIN);
//
//		for (Component comp : comps)
//			if (comp instanceof EffectAble<?>)
//				((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
//		this.showcase.stop();
//		this.showcase.setVisible(false);
//	}
//
//	@Override
//	public void swipeOut() {
//		fade(1, 0, Panels.FADEOUT);
//		for (Component comp : comps)
//			if (comp instanceof EffectAble<?>)
//				((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
//
//	}
//
//	protected void showData(List<CardData> cards, PackType type) {
//
//		switch (type.toString()) {
//		case "I_CLOSED":
//			this.showcase.addData(cards.get(0));
//			break;
//		case "I_OPEN":
//
//			break;
//		case "X_CLOSED":
//
//			break;
//		}
//		this.showcase.swipeIn();
//	}
//}
