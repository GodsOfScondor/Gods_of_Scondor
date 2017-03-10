package scondor.god;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import scondor.mana.ManaType;

public class GodLoader {
	
	private static BufferedReader reader;
	
	private static List<GodData> gods = new ArrayList<>();

	private static String line;
	private final static String PATH="res/data/gods.dat";
	
	private static int id;
	private static String name;
	private static String description;
	private static ManaType type1;
	private static ManaType type2;
	
	/**
	 * 
	 * 
	 * 
	 */
	public static void load() {
		
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH), "UTF-8"));
			id = -1;
			
			while ((line = reader.readLine()) != null) {
				
				if (line.startsWith("[")) {
					id = Integer.parseInt(line.substring(1, line.length()-1));
				} else if (line.startsWith("name=")) {
					name = (line.split("=")[1]);
				} else if (line.startsWith("description=")) {
					description = (line.split("=")[1]);
				} else if (line.startsWith("type1=")) {
					type1 = ManaType.valueOf((line.split("=")[1]));
				} else if (line.startsWith("type2=")) {
					type2 = ManaType.valueOf((line.split("=")[1]));
					if (id != -1) {
						gods.add(new GodData(id, name, description, type1, type2));
					}
				}
				
			}
			
			reader.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static GodData getGod(int id) {
		for (GodData god : gods) return god;
		return null;
	}
	
}
