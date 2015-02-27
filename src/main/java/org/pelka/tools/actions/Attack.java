package org.pelka.tools.actions;

/**
 * A class for holding Attack data
 * 
 * @author Justin Mollenauer
 *
 */
public class Attack {
	private String name;
	private int attackBonus;
	private int damageBonus;
	private int dieNum;
	private int dieType;
	private String type;
	
	/**
	 * Constructor requiring all attack data
	 * @param name
	 * 		Name (Usually Representing the Weapon used)
	 * @param damageBonus
	 * 		Bonus to Damage
	 * @param dieNum
	 * 		Number of Dice
	 * @param dieType
	 * 		Dice Type
	 * @param type
	 * 		Attack Type (Usually Melee or Ranged
	 * @param attackBonus
	 * 		Bonus to Attack
	 */
	public Attack(String name, int damageBonus, int dieNum, int dieType, String type, int attackBonus)
	{
		this.name = name;
		this.damageBonus = damageBonus;
		this.dieNum = dieNum;
		this.dieType = dieType;
		this.type = type;
		this.attackBonus = attackBonus;
	}
	
	/**
	 * Returns attack bonus
	 * @return
	 * 		Attack Bonus
	 */
	public int getAttackBonus() {
		return attackBonus;
	}
	
	/**
	 * Returns type
	 * @return
	 * 		Attack Type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Returns attack name
	 * @return
	 * 		Attack Name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns damage bonus
	 * @return
	 * 		Damage Bonus
	 */
	public int getDamageBonus() {
		return damageBonus;
	}
	
	/**
	 * Returns number of dice
	 * @return
	 * 		Number of Dice
	 */
	public int getDieNum() {
		return dieNum;
	}
	
	/**
	 * Returns dice type
	 * @return
	 * 		Dice Type
	 */
	public int getDieType() {
		return dieType;
	}
}
