package scondor.panels.playground;

import scondor.components.Component;
import scondor.components.Panel;
import scondor.components.Picture;
import scondor.image.Texture;
import scondor.panels.EffectAble;
import scondor.panels.Panels;

public class EnemyHand extends Panel {
	
	private Picture[] enemy_handcards;
	private int n;
	private int MAX_CARDS = 7;
	
	public EnemyHand() {
		super(1);
		enemy_handcards = new Picture[MAX_CARDS];
		Texture tex = new Texture("card_back");
		for (n=0;n<MAX_CARDS;n++) {
			enemy_handcards[n] = new Picture(tex, 0, 130, 80, 110);
			enemy_handcards[n].setLayer(0.1f-0.001f*n);
			add(enemy_handcards[n]);
		}
		
		updateCards(4);
	}
	
	public void updateCards(int amount) {
		for (n=0;n<MAX_CARDS;n++) {
			if (n<amount) {
				enemy_handcards[n].setX(440 - 40*n+((amount*40)/2));
				enemy_handcards[n].setVisible(true);
			} else {
				enemy_handcards[n].setVisible(false);
			}
		}
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
