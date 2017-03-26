package scondor.panels.playground;

import scondor.components.Component;
import scondor.components.Panel;
import scondor.image.Texture;
import scondor.panels.EffectAble;
import scondor.panels.Panels;
import scondor.session.GameType;
import scondor.session.PlayerSideData;

public class Playground extends Panel {
	
	private EnemyHand enemy_hand;
	
	public Playground() {
		super(1);
		super.setBackground(new Texture("colors/white"));
		enemy_hand = new EnemyHand();
		add(enemy_hand);
	}
	
	public void initData(GameType type, String enemy) {
	}
	
	public void updateData(PlayerSideData player, PlayerSideData enemy, String params) {
		
		enemy_hand.updateCards(enemy.getHand().size());
		
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
