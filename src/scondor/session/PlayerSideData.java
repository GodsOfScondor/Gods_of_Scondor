package scondor.session;

import java.io.Serializable;
import java.util.List;

import scondor.deck.card.CardData;
import scondor.deck.card.fieldcard.FieldCardData;
import scondor.deck.card.troops.TroopCardData;
import scondor.god.GodData;

/**
 * 
 * @author Bernhard Scharrer
 * 
 * represents the whole data from one playersie.
 * this class is used for data transfer to the clients.
 *
 */
public class PlayerSideData implements Serializable {
	
	private static final long serialVersionUID = -793364126185832628L;

	private FieldCardData fieldcard;
	
	private List<CardData> hand;
	private List<CardData> stack;
	private List<CardData> graveyard;
	
	private TroopCardData[] attackers;
	private TroopCardData[] defenders;
	
	private GodData god;

	public PlayerSideData(FieldCardData fieldcard, List<CardData> hand, List<CardData> stack, List<CardData> graveyard, TroopCardData[] attackers, TroopCardData[] defenders, GodData god) {
		this.fieldcard = fieldcard;
		this.hand = hand;
		this.stack = stack;
		this.graveyard = graveyard;
		this.attackers = attackers;
		this.defenders = defenders;
		this.god = god;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FieldCardData getFieldcard() {
		return fieldcard;
	}

	public List<CardData> getHand() {
		return hand;
	}

	public List<CardData> getStack() {
		return stack;
	}

	public List<CardData> getGraveyard() {
		return graveyard;
	}

	public TroopCardData[] getAttackers() {
		return attackers;
	}

	public TroopCardData[] getDefenders() {
		return defenders;
	}

	public GodData getGod() {
		return god;
	}
	
}
