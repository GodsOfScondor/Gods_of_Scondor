package scondor.packets;

import java.util.List;

import scondor.deck.card.CardData;
import scondor.gnet.packet.Packet;

public class CardList extends Packet {

	private static final long serialVersionUID = 4715474656181849230L;

	public CardList(List<CardData> list, String params) {
		super("CardList", 2);
		super.addEntry("LIST", list);
		super.addEntry("PARAMS", params);
	}
	
}
