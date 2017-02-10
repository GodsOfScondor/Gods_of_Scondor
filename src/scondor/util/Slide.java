package scondor.util;

import java.util.ArrayList;
import java.util.List;

public class Slide {
	
	/**
	 * 
	 * linearly interpolates between to values
	 * 
	 */
	private int start,end,time;
	private int current;
	private int x;
	private float k;
	private boolean run;
	
	public Slide(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
		slides.add(this);
		run = false;
	}
	
	public void run() {
		run = true;
		current = start;
		x = 0;
		k = (end-start) / ((float) time);
	}
	
	private void refresh() {
		if (run) {
			x++;
			
			current = start + (int) (x*k);
			
			if (x==time) {
				run = false;
				current = end;
			}
		}
	}
	
	public int getValue() {
		return current;
	}
	
	public int getStartValue() {
		return start;
	}
	
	public int getEndValue() {
		return end;
	}
	
	public int getTime() {
		return time;
	}
	
	public void destroy() {
		remove.add(this);
		try { finalize(); }
		catch (Throwable e) { e.printStackTrace(); }
	}
	
	/**
	 * 
	 * slide master - refreshes all slides
	 * 
	 */
	private static List<Slide> slides;
	private static List<Slide> remove;
	
	public static void init() {
		slides = new ArrayList<>();
		remove = new ArrayList<>();
	}
	
	public static void update() {
		for (Slide slide : remove) slides.remove(slide);
		for (Slide slide : slides) slide.refresh();
		remove.clear();
	}
	
}
