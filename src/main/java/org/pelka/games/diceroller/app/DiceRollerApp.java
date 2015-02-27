package org.pelka.games.diceroller.app;

import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.pelka.games.diceroller.calc.DiceRoller;
import org.pelka.tools.actions.Attack;
import org.pelka.tools.util.io.RPGRPFileReader;

/**
 * Main orchestration for the dice roller application.
 * 
 * @author arnoldpelka
 *
 */
public class DiceRollerApp {
	private final static Logger logger = Logger.getLogger(DiceRollerApp.class);

	protected static RPGRPFileReader test;
	protected static DiceRoller roller;
	protected static Attack[] attacks;
	protected static String skillName;
	protected static String fileName;

	/**
	 * The main method for the dice roller application and orchestrator for the
	 * remainder of the application.
	 * 
	 * @param args
	 *            Command line arguments: </br>
	 *            <ol>
	 *            <li>One string value that identifies a type of skill in
	 *            Pathfinder</li>
	 *            <li>One string identifying the name of an available *.rpgrp
	 *            XML input file for use in this tool (include the file
	 *            extension in the name)</li>
	 *            </ol>
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		logger.info("\n|****************************************************|\n|**    RUNNING ORCHESTRATION OF DICE ROLLER APP    **|\n|****************************************************|");

		/*
		 * dr.validateArgs(args); dr.setDiceNumber(args[0]);
		 * dr.setDiceType(args[1]); if (args.length > 2) {
		 * dr.setDiceModifier(args[2]); }
		 * 
		 * dr.printDiceRoller();
		 */
		/*
		 * Read Data
		 */
		skillName = args[0];
		fileName = args[1];
		test = new RPGRPFileReader(fileName);

		/*
		 * Attack
		 */
		// TODO: This only currently runs attack damage rolls by default;
		// include attack roll checks

		logger.info("Fetching Attack Info from character XML file...");
		attacks = test.getAttacks();
		roller = new DiceRoller(attacks[0].getDieNum(), attacks[0].getDieType());

		logger.info("Sending info on " + attacks[0].getName()
				+ " to DiceRoller...");
		roller = new DiceRoller(attacks[0].getDieNum(), attacks[0].getDieType());
		roller.setDiceModifier(attacks[0].getAttackBonus());
		roller.printTotalDiceRoller();

		/*
		 * Skills
		 */
		logger.info("Fetching Skill Info from character XML file...");
		HashMap<String, Integer> skills = test.getSkills();
		roller.setDiceNumber(1);
		roller.setDiceType(20);

		logger.info("Assessing character's available " + skillName
				+ " skill...");
		if (skills.containsKey(skillName)) {
			roller.setDiceModifier(skills.get(skillName));
		} else {
			roller.setDiceModifier(0);
		}

		/*
		 * Rolling
		 */
		logger.info("Rolling for " + skillName + " skill check...");
		roller.printCurrentDiceRoller();
	}
}
