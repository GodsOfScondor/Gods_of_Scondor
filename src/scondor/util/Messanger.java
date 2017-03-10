package scondor.util;

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
	
}
