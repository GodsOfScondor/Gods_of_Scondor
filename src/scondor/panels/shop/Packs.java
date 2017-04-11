//package scondor.panels.shop;
//
//import java.util.ArrayList;
//
//import scondor.components.Picture;
//import scondor.deck.card.CardData;
//import scondor.image.Texture;
//
//public class Packs {
//
//	public static ArrayList<Packs> allPacks = new ArrayList<>();
//
//	private Picture card;
//	private CardData data;
//	private PackType type;
//
//	Texture closed = new Texture("card_gn_common");
//	Texture open = new Texture("arrow");
//
//	public Packs(PackType type, CardData data, int x, int y, int width, int height) {
//		this.data = data;
//		this.type = type;
//
//		switch (type) {
//		case I_CLOSED:
//			card = new Picture(closed, x, y, width, height);
//			break;
//		case X_CLOSED:
//			card = new Picture(closed, x, y, width, height);
//			break;
//		case I_OPEN:
//			card = new Picture(open, x, y, width, height);
//			break;
//		}
//	}
//
//	public CardData getData() {
//		return data;
//	}
//
//	public Picture getPicture() {
//		return card;
//	}
//
//	public PackType getPackType() {
//		return type;
//	}
//}
