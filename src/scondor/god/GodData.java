package scondor.god;

import java.io.Serializable;

import scondor.mana.ManaType;

public class GodData implements Serializable {
	
	private static final long serialVersionUID = 3231458288499536154L;
	private int id;
	private String name;
	private String description;
	private ManaType manatype1;
	private ManaType manatype2;

	public GodData(int id, String name, String description, ManaType manatype1, ManaType manatype2) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.manatype1=manatype1;
		this.manatype2=manatype2;
	}
	
	public GodData cloneGod() {
		try { return (GodData) this.clone(); }
		catch (CloneNotSupportedException e) { e.printStackTrace(); }
		return null;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public ManaType getManatype1() {
		return manatype1;
	}

	public ManaType getManatype2() {
		return manatype2;
	}
	
}
