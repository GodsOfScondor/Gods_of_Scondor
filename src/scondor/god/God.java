package scondor.god;

import scondor.mana.ManaType;

public class God {
	
	private ManaType manatype1;
	private ManaType manatype2;
	private int level;
	private int exp;
	private int manatype1_amount;
	private int manatype2_amount;
	private int universal_mana_amount;

	public God(int level, int exp, ManaType manatype1, ManaType manatype2, int manatype1_amount, int manatype2_amount, int universal_mana_amount){
		this.level=level;
		this.exp=exp;
		this.manatype1=manatype1;
		this.manatype2=manatype2;
		this.manatype1_amount=manatype1_amount;
		this.manatype2_amount=manatype2_amount;
		this.universal_mana_amount=universal_mana_amount;
	}
	
	public ManaType getManatype1() {
		return manatype1;
	}

	public void setManatype1(ManaType manatype1) {
		this.manatype1 = manatype1;
	}

	public ManaType getManatype2() {
		return manatype2;
	}

	public void setManatype2(ManaType manatype2) {
		this.manatype2 = manatype2;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getEXP() {
		return exp;
	}

	public void setEXP(int exp) {
		this.exp = exp;
	}

	public int getManatype1_amount() {
		return manatype1_amount;
	}

	public void setManatype1_amount(int manatype1_amount) {
		this.manatype1_amount = manatype1_amount;
	}

	public int getManatype2_amount() {
		return manatype2_amount;
	}

	public void setManatype2_amount(int manatype2_amount) {
		this.manatype2_amount = manatype2_amount;
	}

	public int getUniversal_mana_amount() {
		return universal_mana_amount;
	}

	public void setUniversal_mana_amount(int universal_mana_amount) {
		this.universal_mana_amount = universal_mana_amount;
	}
	
}
