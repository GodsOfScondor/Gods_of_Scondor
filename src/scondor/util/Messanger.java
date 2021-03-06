package scondor.util;

import scondor.components.Containers;

public class Messanger {
	
	public static String build(String msg, float r, float g, float b) {
		return r+","+g+","+b+":"+msg;
	}
	
	public static String build(String msg, int panel, float r, float g, float b) {
		return r+","+g+","+b+":"+panel+":"+msg;
	}
	
	public static String nopopup() {
		return "nopopup";
	}
	
	public static void popup(String msg) {
		
		if (msg.equalsIgnoreCase("nopopup")) return;
		
		String[] buffer = msg.split(":");
		
		if (buffer.length==2) {
			Containers.popup(buffer[1], Float.parseFloat(buffer[0].split(",")[0]), Float.parseFloat(buffer[0].split(",")[1]), Float.parseFloat(buffer[0].split(",")[2]), -1);
		} else {
			Containers.popup(buffer[2], Float.parseFloat(buffer[0].split(",")[0]), Float.parseFloat(buffer[0].split(",")[1]), Float.parseFloat(buffer[0].split(",")[2]), Integer.parseInt(buffer[1]));
		}
	}
	
}
