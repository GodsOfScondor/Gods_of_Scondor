package scondor.deck.card.troops;

import scondor.deck.card.Card;

public abstract class TroopCard extends Card {

	private int attack;
	private int live;
	private int countdown;

	public TroopCard(int mana_cost, int attack, int live, int countdown) {
		super(mana_cost);
		this.attack = attack;
		this.live = live;
		this.countdown = countdown;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getLive() {
		return live;
	}

	public void setLive(int defense) {
		this.live = defense;
	}

	public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int count) {
		this.countdown = count;
	}
	
}