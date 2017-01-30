package scondor.deck.card;

public abstract class Card {
	
	private int mana_cost;
	
	public Card(int mana_cost){
		this.mana_cost = mana_cost;
	}
	
	public int getManaCost(){
		return mana_cost;
	}

	public void setManaCost(int mana_cost){
		this.mana_cost = mana_cost;
	}
	
}
