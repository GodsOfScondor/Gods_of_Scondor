package scondor.mana;

import java.io.Serializable;

public class ManaData implements Serializable {
	
	private static final long serialVersionUID = 9143842828006235122L;
	
	private int mana1;
	private int mana2;
	private int mana_uni;
	
	public ManaData() {
		this.mana1 = 0;
		this.mana2 = 0;
		this.mana_uni = 0;
	}

	public int getMana1() {
		return mana1;
	}

	public void setMana1(int mana1) {
		this.mana1 = mana1;
	}

	public int getMana2() {
		return mana2;
	}

	public void setMana2(int mana2) {
		this.mana2 = mana2;
	}

	public int getMana_uni() {
		return mana_uni;
	}

	public void setMana_uni(int mana_uni) {
		this.mana_uni = mana_uni;
	}
	
}
