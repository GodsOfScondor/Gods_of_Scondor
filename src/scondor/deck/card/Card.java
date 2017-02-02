package scondor.deck.card;

import scondor.mana.ManaType;

public abstract class Card {
	
	private int mana_cost;
	private ManaType mana_type;
	
	public Card(int mana_cost,ManaType mana_type){
		this.mana_type = mana_type;
		this.mana_cost = mana_cost;
	}

	public int getManaCost(){
		return mana_cost;
	}

	public void setManaCost(int mana_cost){
		this.mana_cost = mana_cost;
	}

	public ManaType getManatype() {
		return mana_type;
	}

	public void setManatype(ManaType manatype) {
		this.mana_type = manatype;
	}
	
}
