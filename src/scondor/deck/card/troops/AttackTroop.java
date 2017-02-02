package scondor.deck.card.troops;

import scondor.mana.ManaType;

public class AttackTroop extends TroopCard {

	private int countdown;
	
	public AttackTroop(int mana_cost, int attack, int defense, ManaType mana_type, int countdown, int fcode) {
		super(mana_cost, attack, defense, mana_type, fcode);
		this.setCountdown(countdown);
	}

	public void attack(TroopCard troop) {

	}

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}

}
