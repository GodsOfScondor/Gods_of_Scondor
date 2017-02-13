package scondor.packets;

import scondor.gnet.packet.Packet;

public class Verification extends Packet {

	private static final long serialVersionUID = -1234507930300190636L;

	public Verification(String username, String password, String license) {
		super("Verification", 3);
		super.addEntry("USERNAME", username);
		super.addEntry("PASSWORD", password);
		super.addEntry("LICENSE", license);
	}

}
