package scondor.packets;

import java.util.List;

import scondor.gnet.packet.Packet;

public class Data extends Packet {

	private static final long serialVersionUID = 5746561272304106106L;

	public Data(String name, List<String> data) {
		super("Data", 2);
		super.addEntry("NAME", name);
		super.addEntry("DATA", data);
	}

}
