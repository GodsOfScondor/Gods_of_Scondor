//package scondor.panels.playground;
//
//import scondor.components.Component;
//import scondor.components.Panel;
//import scondor.components.Picture;
//import scondor.image.Texture;
//import scondor.panels.EffectAble;
//import scondor.panels.Panels;
//
//public class EnemyHand extends Panel {
//	
//	private Picture[] handcards;
//	private int n;
//	private int MAX_CARDS = 8;
//	
//	public EnemyHand() {
//		super(1);
//		handcards = new Picture[MAX_CARDS];
//		Texture tex = new Texture("card_back");
//		for (n=0;n<MAX_CARDS;n++) {
//			handcards[n] = new Picture(tex, -100, -60, 80, 110);
//			handcards[n].setLayer(0.1f-0.001f*n);
//			add(handcards[n]);
//		}
//		
//	}
//	
//	public void updateCards(int amount) {
//		for (n=0;n<MAX_CARDS;n++) {
//			if (n<amount) {
//				handcards[n].setX(440 - 40*n+((amount*40)/2));
//				handcards[n].setVisible(true);
//			} else {
//				handcards[n].setVisible(false);
//			}
//		}
//	}
//
//	@Override
//	public void swipeIn() {
//		fade(0, 1, Panels.FADEIN);
//		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
//	}
//
//	@Override
//	public void swipeOut() {
//		fade(1, 0, Panels.FADEOUT);
//		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
//	}
//	
//}
