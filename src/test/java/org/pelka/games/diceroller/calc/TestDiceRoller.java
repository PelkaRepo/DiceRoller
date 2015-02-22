package org.pelka.games.diceroller.calc;

import static org.junit.Assert.*;

import org.pelka.games.diceroller.calc.DiceRoller;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDiceRoller {
	protected DiceRoller defaultConstruction;
	protected DiceRoller presetConstruction;
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		defaultConstruction = new DiceRoller();
		presetConstruction = new DiceRoller(7,20);
	}

	@After
	public void tearDown() throws Exception {
		defaultConstruction.setDiceNumber(0);
		defaultConstruction.setDiceModifier(0);
		defaultConstruction.setDiceType(0);
	}

	@Test
	public void testSetDiceNumberString() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceNumber(),0);
		assertNotEquals(defaultConstruction.getDiceNumber(),"0");
		
		defaultConstruction.setDiceNumber("2");
		assertEquals(defaultConstruction.getDiceNumber(),2);
		assertNotEquals(defaultConstruction.getDiceNumber(),"2");
		
		defaultConstruction.setDiceNumber("-2");
		assertEquals(defaultConstruction.getDiceNumber(),2);
		assertNotEquals(defaultConstruction.getDiceNumber(),-2);
		assertNotEquals(defaultConstruction.getDiceNumber(),"2");
		assertNotEquals(defaultConstruction.getDiceNumber(),"-2");
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceNumber(),7);
		assertNotEquals(presetConstruction.getDiceNumber(),"7");
		assertNotEquals(presetConstruction.getDiceNumber(),0);
	}

	@Test
	public void testSetDiceNumberInt() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceNumber(),0);
		assertNotEquals(defaultConstruction.getDiceNumber(),"0");
		
		defaultConstruction.setDiceNumber(2);
		assertEquals(defaultConstruction.getDiceNumber(),2);
		assertNotEquals(defaultConstruction.getDiceNumber(),"2");
		
		defaultConstruction.setDiceNumber(-2);
		assertEquals(defaultConstruction.getDiceNumber(),2);
		assertNotEquals(defaultConstruction.getDiceNumber(),-2);
		assertNotEquals(defaultConstruction.getDiceNumber(),"2");
		assertNotEquals(defaultConstruction.getDiceNumber(),"-2");
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceNumber(),7);
		assertNotEquals(presetConstruction.getDiceNumber(),"7");
		assertNotEquals(presetConstruction.getDiceNumber(),0);
	}

	@Test
	public void testSetDiceTypeString() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceType(),0);
		assertNotEquals(defaultConstruction.getDiceType(),"0");
		
		defaultConstruction.setDiceType("12");
		assertEquals(defaultConstruction.getDiceType(),12);
		assertNotEquals(defaultConstruction.getDiceType(),"12");
		
		defaultConstruction.setDiceType("-12");
		assertEquals(defaultConstruction.getDiceType(),12);
		assertNotEquals(defaultConstruction.getDiceType(),-12);
		assertNotEquals(defaultConstruction.getDiceType(),"12");
		assertNotEquals(defaultConstruction.getDiceType(),"-12");
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceType(),20);
		assertNotEquals(presetConstruction.getDiceType(),"20");
		assertNotEquals(presetConstruction.getDiceType(),0);
	}

	@Test
	public void testSetDiceTypeInt() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceType(),0);
		assertNotEquals(defaultConstruction.getDiceType(),"0");
		
		defaultConstruction.setDiceType(12);
		assertEquals(defaultConstruction.getDiceType(),12);
		assertNotEquals(defaultConstruction.getDiceType(),"12");
		
		defaultConstruction.setDiceType(-12);
		assertEquals(defaultConstruction.getDiceType(),12);
		assertNotEquals(defaultConstruction.getDiceType(),-12);
		assertNotEquals(defaultConstruction.getDiceType(),"12");
		assertNotEquals(defaultConstruction.getDiceType(),"-12");
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceType(),20);
		assertNotEquals(presetConstruction.getDiceType(),"20");
		assertNotEquals(presetConstruction.getDiceType(),0);
	}

	@Test
	public void testSetDiceModifierString() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceModifier(),0);
		assertNotEquals(defaultConstruction.getDiceModifier(),"0");
		
		defaultConstruction.setDiceModifier("12");
		assertEquals(defaultConstruction.getDiceModifier(),12);
		assertNotEquals(defaultConstruction.getDiceModifier(),"12");
		
		defaultConstruction.setDiceModifier("-12");
		assertNotEquals(defaultConstruction.getDiceModifier(),12);
		assertEquals(defaultConstruction.getDiceModifier(),-12);
		assertNotEquals(defaultConstruction.getDiceModifier(),"12");
		assertNotEquals(defaultConstruction.getDiceModifier(),"-12");
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceModifier(),0);
		assertNotEquals(presetConstruction.getDiceModifier(),"0");
		assertNotEquals(presetConstruction.getDiceModifier(),20);
		assertNotEquals(presetConstruction.getDiceModifier(),"20");
	}

	@Test
	public void testSetDiceModifierInt() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceModifier(),0);
		assertNotEquals(defaultConstruction.getDiceModifier(),"0");
		
		defaultConstruction.setDiceModifier(12);
		assertEquals(defaultConstruction.getDiceModifier(),12);
		assertNotEquals(defaultConstruction.getDiceModifier(),"12");
		
		defaultConstruction.setDiceModifier(-12);
		assertNotEquals(defaultConstruction.getDiceModifier(),12);
		assertEquals(defaultConstruction.getDiceModifier(),-12);
		assertNotEquals(defaultConstruction.getDiceModifier(),"12");
		assertNotEquals(defaultConstruction.getDiceModifier(),"-12");
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceModifier(),0);
		assertNotEquals(presetConstruction.getDiceModifier(),"0");
		assertNotEquals(presetConstruction.getDiceModifier(),20);
		assertNotEquals(presetConstruction.getDiceModifier(),"20");
	}

	@Test
	public void testGetDiceNumber() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceNumber(),0);
		assertNotEquals(defaultConstruction.getDiceNumber(),"0");
		
		defaultConstruction.setDiceNumber(2);
		assertEquals(defaultConstruction.getDiceNumber(),2);
		assertNotEquals(defaultConstruction.getDiceNumber(),4);
		
		defaultConstruction.setDiceNumber(-2);
		assertEquals(defaultConstruction.getDiceNumber(),2);
		assertNotEquals(defaultConstruction.getDiceNumber(),-2);
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceNumber(),7);
		assertNotEquals(presetConstruction.getDiceNumber(),0);
	}

	@Test
	public void testGetDiceType() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceType(),0);
		assertNotEquals(defaultConstruction.getDiceType(),"0");
		
		defaultConstruction.setDiceType(2);
		assertEquals(defaultConstruction.getDiceType(),2);
		assertNotEquals(defaultConstruction.getDiceType(),4);
		
		defaultConstruction.setDiceType(-2);
		assertEquals(defaultConstruction.getDiceType(),2);
		assertNotEquals(defaultConstruction.getDiceType(),-2);
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceType(),20);
		assertNotEquals(presetConstruction.getDiceType(),0);
	}

	@Test
	public void testGetDiceModifier() {
		/*
		 * Default Construction
		 */
		assertEquals(defaultConstruction.getDiceModifier(),0);
		assertNotEquals(defaultConstruction.getDiceModifier(),"0");
		
		defaultConstruction.setDiceModifier(2);
		assertEquals(defaultConstruction.getDiceModifier(),2);
		assertNotEquals(defaultConstruction.getDiceModifier(),4);
		
		defaultConstruction.setDiceModifier(-2);
		assertNotEquals(defaultConstruction.getDiceModifier(),2);
		assertEquals(defaultConstruction.getDiceModifier(),-2);
		
		/*
		 * Preset Construction
		 */
		assertEquals(presetConstruction.getDiceModifier(),0);
		assertNotEquals(presetConstruction.getDiceModifier(),9);
	}

	@Test
	public void testRunTotalDiceRoller() {
		/*
		 * Simple path
		 */
		defaultConstruction.setDiceNumber(1);
		defaultConstruction.setDiceType(6);
		defaultConstruction.setDiceModifier(0);
		int minExpectedResult = 1*1+0;
		int maxExpectedResult = 1*6+0;
		int actualResult = defaultConstruction.runTotalDiceRoller();
		
		for (int i=0; i < 1000; i++) {
			assertTrue("Result was not within expected random bounds",(actualResult >= minExpectedResult)||(actualResult <= maxExpectedResult));
		}
		
		/*
		 * Path with positive modifier
		 */
		defaultConstruction.setDiceNumber(2);
		defaultConstruction.setDiceType(12);
		defaultConstruction.setDiceModifier(5);
		minExpectedResult = 2*1+5;
		maxExpectedResult = 2*12+5;
		actualResult = defaultConstruction.runTotalDiceRoller();
		
		for (int i=0; i < 1000; i++) {
			assertTrue("Result was not within expected random bounds",(actualResult >= minExpectedResult)||(actualResult <= maxExpectedResult));
		}
		
		/*
		 * Path with negative modifier
		 */
		defaultConstruction.setDiceNumber(4);
		defaultConstruction.setDiceType(20);
		defaultConstruction.setDiceModifier(-7);
		minExpectedResult = 4*1+(-7);
		maxExpectedResult = 4*20+(-7);
		actualResult = defaultConstruction.runTotalDiceRoller();
		
		for (int i=0; i < 1000; i++) {
			assertTrue("Result was not within expected random bounds",(actualResult >= minExpectedResult)||(actualResult <= maxExpectedResult));
		}
	}

	@Test
	public void testRunCurrentDiceRoller() {
		/*
		 * Simple path
		 */
		defaultConstruction.setDiceType(6);
		defaultConstruction.setDiceModifier(0);
		int minExpectedResult = 1+0;
		int maxExpectedResult = 6+0;
		int actualResult = defaultConstruction.runTotalDiceRoller();
		
		for (int i=0; i < 1000; i++) {
			assertTrue("Result was not within expected random bounds",(actualResult >= minExpectedResult)||(actualResult <= maxExpectedResult));
		}
		
		/*
		 * Path with positive modifier
		 */
		defaultConstruction.setDiceType(12);
		defaultConstruction.setDiceModifier(5);
		minExpectedResult = 1+5;
		maxExpectedResult = 12+5;
		actualResult = defaultConstruction.runTotalDiceRoller();
		
		for (int i=0; i < 1000; i++) {
			assertTrue("Result was not within expected random bounds",(actualResult >= minExpectedResult)||(actualResult <= maxExpectedResult));
		}
		
		/*
		 * Path with negative modifier
		 */
		defaultConstruction.setDiceType(20);
		defaultConstruction.setDiceModifier(-7);
		minExpectedResult = 1+(-7);
		maxExpectedResult = 20+(-7);
		actualResult = defaultConstruction.runTotalDiceRoller();
		
		for (int i=0; i < 1000; i++) {
			assertTrue("Result was not within expected random bounds",(actualResult >= minExpectedResult)||(actualResult <= maxExpectedResult));
		}
	}

}
