package scondor.deck.card.troops;

import scondor.deck.card.CardData;
import scondor.mana.ManaType;

public class TroopCardData extends CardData {
	
	private static final long serialVersionUID = -1758970014931750800L;
	private int attack, live, countdown;
	
	/**
	 *
	 * repesents raw data of a troop card
	 * 
	 */
	public TroopCardData(int id, String name, String description, int mana_cost, ManaType mana_type, int attack, int live, int countdown) {
		super(id, name, description, mana_cost, mana_type);
		this.attack = attack;
		this.live = live;
		this.countdown = countdown;
	}

	public int getAttack() {
		return attack;
	}

	public int getLive() {
		return live;
	}

	public int getCountdown() {
		return countdown;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setLive(int live) {
		this.live = live;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}

	@Override
	public CardData cloneCard() {
		return new TroopCardData(attack, name, description, attack, mana_type, attack, live, countdown);
	}
	
}
