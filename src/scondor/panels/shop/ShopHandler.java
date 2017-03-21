package scondor.panels.shop;

import scondor.packets.Message;
import scondor.panels.Panels;
import scondor.server.Client;
import scondor.util.Messanger;

public class ShopHandler {
	
	private static boolean buying;
	private static final int SHOP_SUCCES = 5;
	private static final int SHOP_FAIL = 6;
	
	public static void buy(String data) {
		if (!buying) {
			buying=true;
			Client.send(new Message("buy;"+data+";"));
		}
	}
	
	public static String msgFromServer(int code) {
		
		buying = false;
		
		if (code==SHOP_SUCCES) {
			
			return Messanger.build("Succesfully bought!", Panels.SHOP, 0, 1, 0);
			
		} else if (code==SHOP_FAIL) {
			
			return Messanger.build("Not enough money!", Panels.SHOP, 1, 0, 0);
			
		}
		
		return Messanger.nopopup();
	}
	
}