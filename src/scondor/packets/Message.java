package scondor.packets;

import org.gnet.packet.Packet;

public class Message extends Packet {

	private static final long serialVersionUID = -2230724361755119445L;

	public Message(String message) {
		super("Message", 1);
		super.addEntry("MESSAGE", message);
	}
}
