package scondor.packets;

import scondor.gnet.packet.Packet;
import scondor.session.PlayerSideData;

public class State extends Packet {

	private static final long serialVersionUID = -1288298779687163575L;

	public State(PlayerSideData player1, PlayerSideData player2, String params) {
		super("GAMESTATE", 2);
		super.addEntry("PLAYER1", player1);
		super.addEntry("PLAYER2", player2);
		super.addEntry("PARAMS", params);
	}
	
}
