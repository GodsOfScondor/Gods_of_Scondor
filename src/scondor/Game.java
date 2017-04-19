package scondor;

import scondor.components.Containers;

public class Game {
	
	private static long ago;
	
	public static void init() {
		
		Containers.init();
		
	}
	
	public static void update() {
		ago = System.currentTimeMillis();
		Containers.update();
		System.out.println(System.currentTimeMillis()-ago);
	}
	
	public static void close() {
		
	}
	
}
