package org.pelka.tools.rpgrpfile.app;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.pelka.tools.rpgrpfile.calc.*;
import org.pelka.games.diceroller.calc.DiceRoller;

/**
 * A simple app testing rpgrp reading and communication with the DiceRoller class
 * 
 * @author Justin Mollenauer
 *
 */
public class RPGRPFileApp
{
	private final static Logger logger = Logger.getLogger(RPGRPFileApp.class);
	
	public static void main(String[] args)
	{
		RPGRPFile test = new RPGRPFile("Magnis Of Hornwood.rpgrp");
		logger.info("Fetching Attack Info...");
		Attack[] attacks = test.getAttacks();
		logger.info("Sending info on " + attacks[0].getName() + " to DiceRoller");
		DiceRoller roller = new DiceRoller(attacks[0].getDieNum(), attacks[0].getDieType());
		roller.setDiceModifier(attacks[0].getAttackBonus());
		roller.printDiceRoller();
		logger.info("Fetching Skill Info");
		HashMap<String, Integer> skills = test.getSkills();
		roller.setDiceNumber(1);
		roller.setDiceType(20);
		if(skills.containsKey("Bluff"))
		{
			roller.setDiceModifier(skills.get("Bluff"));
		}
		else
		{
			roller.setDiceModifier(0);
		}
		logger.info("Rolling Bluff...");
		roller.printDiceRoller();
	}
}