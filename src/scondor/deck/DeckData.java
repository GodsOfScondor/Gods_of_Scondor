package scondor.deck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import scondor.god.GodData;

public class DeckData implements Serializable{
	
	private static final long serialVersionUID = -6375857007307014894L;
	private int id;
	private String name;
	private List<Integer> deck = new ArrayList<>();
	private GodData god;
	
	public DeckData(int id, String name, List<Integer> deck, GodData data) {
		this.id = id;
		this.name = name;
		this.deck = deck;
		this.god = data;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	protected List<Integer> getCards() {
		return deck;
	}
	
	public GodData getGod() {
		return god;
	}


}