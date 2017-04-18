package scondor.deck.card.troops;

import scondor.deck.card.CardData;
import scondor.mana.ManaType;

public class DTCardData extends CardData {
	
	private static final long serialVersionUID = -999784362299269997L;
	private int attack, live;
	
	/**
	 *
	 * repesents raw data of a troop card
	 * 
	 */
	public DTCardData(int id, String name, String description, int mana_cost, ManaType mana_type, int attack, int live) {
		super(id, name, description, mana_cost, mana_type);
		this.attack = attack;
		this.live = live;
	}

	public int getAttack() {
		return attack;
	}

	public int getLive() {
		return live;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setLive(int live) {
		this.live = live;
	}

	@Override
	public CardData cloneCard() {
		return new DTCardData(id, name, description, attack, mana_type, attack, live);
	}
	
}
