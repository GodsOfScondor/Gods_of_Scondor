package scondor.packets;

import java.util.List;

import scondor.deck.DeckData;
import scondor.gnet.packet.Packet;

public class DeckList extends Packet {

	private static final long serialVersionUID = -7170930832747997288L;

	public DeckList(List<DeckData> list, String params) {
		super("DeckList", 2);
		super.addEntry("LIST", list);
		super.addEntry("PARAMS", params);
	}
	
}
