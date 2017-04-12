package scondor.panels.shop;

import java.util.List;

import scondor.components.Containers;
import scondor.deck.card.CardData;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Messanger;

public class ShopHandler {

	private static boolean buying;
	private static final int SHOP_SUCCES = 5;
	private static final int SHOP_FAIL = 6;
	private static int money;

	public static void buy(PackType type) {
		if (!buying) {
			buying = true;
			Client.sendToServer(new Message("buy;" + type.toString()));
		}
	}

	public static String msgFromServer(int code) {

		buying = false;

		if (code == SHOP_SUCCES) {

		} else if (code == SHOP_FAIL) {

//			return Messanger.build("Not enough money!", Panels.SHOP, 1, 0, 0);

		}

		return Messanger.nopopup();
	}

	public static void updateMoney(int money) {
		ShopHandler.money = money;
	}

	/**
	 * 
	 * 
	 *
	 * 
	 */
	public static int getMoney() {
		return money;
	}

	/*
	 * 
	 * 
	 * 
	 */
	public static void incomingData(List<CardData> cards, PackType type) {
		Containers.getShop().incomingData(cards, type);
	}
	
}
