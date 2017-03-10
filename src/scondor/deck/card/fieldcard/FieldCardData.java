package scondor.deck.card.fieldcard;

import scondor.deck.card.CardData;
import scondor.mana.ManaType;

public class FieldCardData extends CardData {

	private static final long serialVersionUID = -1992636925239749138L;
	private int linger;
	
	/**
	 *
	 * repesents raw data of a field card
	 * 
	 */
	public FieldCardData(int id, String name, String description, int mana_cost, ManaType mana_type, int linger) {
		super(id, name, description, mana_cost, mana_type);
		this.linger = linger;
	}
	
	public int getLinger() {
		return linger;
	}

	public void setLinger(int linger) {
		this.linger = linger;
	}
	
}