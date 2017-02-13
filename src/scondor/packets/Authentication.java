package scondor.packets;

import scondor.gnet.packet.Packet;

public class Authentication extends Packet {

	private static final long serialVersionUID = 7635961373279522217L;

	public Authentication(String username, String password) {
		super("Authentification", 2);
		super.addEntry("USERNAME", username);
		super.addEntry("PASSWORD", password);
	}
	
}
