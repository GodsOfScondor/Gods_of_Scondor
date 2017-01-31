package scondor.player;

import java.util.ArrayList;
import java.util.List;

import scondor.deck.Deck;
import scondor.deck.card.Card;

public class Player {
	private Deck player_deck;
	private List<Card> card_list=new ArrayList<>();
	
	public Player(){
		this.setPlayer_deck(new Deck(card_list));
	}

	public Deck getPlayer_deck() {
		return player_deck;
	}

	public void setPlayer_deck(Deck player_deck) {
		this.player_deck = player_deck;
	}
	
}
