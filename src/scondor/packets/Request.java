package scondor.packets;

import scondor.gnet.packet.Packet;

public class Request extends Packet {

	private static final long serialVersionUID = 7854946788993946181L;

	public Request(String request) {
		super("Request", 1);
		super.addEntry("REQUEST", request);
	}

}
