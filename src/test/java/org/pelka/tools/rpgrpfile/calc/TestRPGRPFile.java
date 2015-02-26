package org.pelka.tools.rpgrpfile.calc;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.pelka.games.diceroller.calc.DiceRoller;

/**
 * Testing individual methods of the RPGRPFile class. This will also run a
 * practical use case of the class using test data.
 * 
 * @author arnoldpelka, Justin Mollenauer
 *
 */
public class TestRPGRPFile {
	private final static Logger logger = Logger.getLogger(TestRPGRPFile.class);

	protected RPGRPFile test;
	protected DiceRoller roller;
	protected Attack[] attacks;

	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		test = new RPGRPFile("Magnis Of Hornwood.rpgrp");
	}

	@After
	public void tearDown() throws Exception {
		test = null;
		roller = null;
	}

	/**
	 * Testing full sample iteration of use of RPG RP File reading and attack
	 * execution. Actual implementation should look similar to this.
	 */
	@Test
	public void testRPGRPFile() {
		logger.info("Fetching Attack Info from character XML file...");
		attacks = test.getAttacks();
		roller = new DiceRoller(attacks[0].getDieNum(), attacks[0].getDieType());

		logger.info("Sending info on " + attacks[0].getName()
				+ " to DiceRoller...");
		roller = new DiceRoller(attacks[0].getDieNum(), attacks[0].getDieType());
		roller.setDiceModifier(attacks[0].getAttackBonus());
		roller.printDiceRoller();

		logger.info("Fetching Skill Info from character XML file...");
		HashMap<String, Integer> skills = test.getSkills();
		roller.setDiceNumber(1);
		roller.setDiceType(20);

		logger.info("Assessing character's available bluff skill...");
		if (skills.containsKey("Bluff")) {
			roller.setDiceModifier(skills.get("Bluff"));
		} else {
			roller.setDiceModifier(0);
		}

		logger.info("Rolling Bluff...");
		roller.printDiceRoller();

		/*
		 * Running unit tests
		 */
		assertTrue("Attack bonus modifier did not match the expected value",
				(attacks[0].getAttackBonus() == 7));
		assertTrue("Attack die number did not match the expected value",
				(attacks[0].getDieNum() == 1));
		assertTrue("Attack die number did not match the expected value",
				(attacks[0].getDieType() == 6));
		assertTrue("Attack name did not match the expected value",
				(attacks[0].getName()
						.equalsIgnoreCase("Masterwork Sword (Short)")));
		assertTrue("Skill value did not match the expected value",
				(skills.get("Bluff") == 4));
		assertTrue("Dice modifier value did not match the expected value",
				(roller.getDiceModifier() == 4));
		assertTrue("Result was not within expected random bounds",
				(roller.getCurrentRoll() >= 1)
						|| (roller.getCurrentRoll() <= 20));
	}

	/**
	 * Test method for
	 * {@link org.pelka.tools.rpgrpfile.calc.RPGRPFile#getName()}.
	 */
	@Test
	public void testGetName() {
		attacks = test.getAttacks();

		assertTrue("Attack name did not match the expected value",
				(attacks[0].getName()
						.equalsIgnoreCase("Masterwork Sword (Short)")));
		assertFalse("Attack name did not match the expected value",
				(attacks[0].getName().equalsIgnoreCase("lasjkdf")));
	}

	/**
	 * Test method for
	 * {@link org.pelka.tools.rpgrpfile.calc.RPGRPFile#getInitiative()}.
	 */
	@Test
	public void testGetInitiative() {
		assertTrue("Initiative value did not match the expected value", test
				.getInitiative().equalsIgnoreCase("-1"));
		assertFalse("Initiative value did not match the expected value",
				test.getInitiative() == "-1");
		assertFalse("Initiative value did not match the expected value", test
				.getInitiative().equalsIgnoreCase("+1"));
		assertFalse("Initiative value did not match the expected value", test
				.getInitiative().equalsIgnoreCase("1"));
		assertFalse("Initiative value did not match the expected value", test
				.getInitiative().equalsIgnoreCase("4"));
		assertFalse("Initiative value did not match the expected value", test
				.getInitiative().equalsIgnoreCase("alsdkf"));
	}

	/**
	 * Test method for
	 * {@link org.pelka.tools.rpgrpfile.calc.RPGRPFile#getAttacks()}.
	 */
	@Test
	public void testGetAttacks() {
		attacks = test.getAttacks();

		/*
		 * Attack bonus
		 */
		assertTrue("Attack bonus modifier did not match the expected value",
				(attacks[0].getAttackBonus() == 7));
		assertFalse("Attack bonus modifier did not match the expected value",
				(attacks[0].getAttackBonus() == -7));
		assertFalse("Attack bonus modifier did not match the expected value",
				(attacks[0].getAttackBonus() == 6));

		/*
		 * Number of attack die
		 */
		assertTrue("Attack die number did not match the expected value",
				(attacks[0].getDieNum() == 1));
		assertFalse("Attack die number did not match the expected value",
				(attacks[0].getDieNum() == -1));
		assertFalse("Attack die number did not match the expected value",
				(attacks[0].getDieNum() == 3));

		/*
		 * Attack die type
		 */
		assertTrue("Attack die number did not match the expected value",
				(attacks[0].getDieType() == 6));
		assertFalse("Attack die number did not match the expected value",
				(attacks[0].getDieType() == -6));
		assertFalse("Attack die number did not match the expected value",
				(attacks[0].getDieType() == 8));

		/*
		 * Attack name
		 */
		assertTrue("Attack name did not match the expected value",
				(attacks[0].getName()
						.equalsIgnoreCase("Masterwork Sword (Short)")));
		assertFalse("Attack name did not match the expected value",
				(attacks[0].getName() == "Masterwork Sword (Short)"));
		assertFalse("Attack name did not match the expected value",
				(attacks[0].getName().equalsIgnoreCase("alskdjg")));
	}

	/**
	 * Test method for
	 * {@link org.pelka.tools.rpgrpfile.calc.RPGRPFile#getSkills()}.
	 */
	@Test
	public void testGetSkills() {
		HashMap<String, Integer> skills = test.getSkills();

		assertTrue("Skill value did not match the expected value",
				(skills.get("Bluff") == 4));
		assertTrue("Skill value did not match the expected value",
				(skills.get("Climb") == -4));
		assertTrue("Skill value did not match the expected value",
				(skills.get("Perception") == 0));
		assertFalse("Skill value did not match the expected value",
				(skills.get("Bluff") == -4));
		assertFalse("Skill value did not match the expected value",
				(skills.get("Climb") == 4));
		assertFalse("Skill value did not match the expected value",
				(skills.get("Perception") == 1));
	}

}
