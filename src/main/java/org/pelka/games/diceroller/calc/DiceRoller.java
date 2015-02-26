package org.pelka.games.diceroller.calc;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Dice rolling calculator. This will either be used for just printing the rolls
 * or as a utility for a greater design.
 * 
 * @author arnoldpelka
 *
 */
public class DiceRoller {
	private final static Logger logger = Logger.getLogger(DiceRoller.class);
	protected int diceNumber = 0;
	protected int diceType = 0;
	protected int diceModifier = 0;
	protected int currentRoll = 0;
	protected int totalDiceRoll = 0;

	/**
	 * Default constructor for instantiating the dice roller calculators
	 */
	public DiceRoller() {
		logger.info("Default Dice Roller Constructed");
	}

	/**
	 * Constructor for instantiating the dice roller calculators with most dice
	 * details, such as the number of dice and the type of dice to be rolled.
	 * These can also be mutated by standard mutators when using the default
	 * constructor, or overwritten when using this constructor after variables
	 * are instantiated.
	 * 
	 * @param diceNumber
	 *            The number of dice to be rolled
	 * @param diceType
	 *            The number of faces a die will have when rolled
	 */
	public DiceRoller(int diceNumber, int diceType) {
		logger.info("Dice Roller Constructed with Dice Details");
		this.diceNumber = diceNumber;
		this.diceType = diceType;
	}

	/**
	 * A simple live validator method that will evaluate an array of strings for
	 * their ability to be converted to numbers which can be calculated against.
	 * Failing these tests will fail the entire application and return from the
	 * JVM.
	 * 
	 * This validation is only available for an array of strings, presumably
	 * when run from the command line as a simple application.
	 * 
	 * @param args
	 *            An array of strings to be tested
	 */
	@SuppressWarnings("unused")
	public void validateArgs(String[] args) {
		int tempValue = 0;

		try {
			for (int i = 0; i < args.length; i++) {
				tempValue = Math.abs(Integer.parseInt(args[i]));
			}
			if ((args == null) || (args.length < 2)) {
				throw new ArrayIndexOutOfBoundsException();
			}
		} catch (NullPointerException npe) {
			logger.error("Please ensure that you provide all command line arguments at run time or use the mutator methods to set all available calculable variables before use or programmatic consumption, respectively.");
			logger.debug("This validator is only meant for evaluating string-based inputs.  If you do not need to accept string input, please use the available integer mutator methods and constructors to use the dice roller calculator fascets.");
			System.exit(0);
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			logger.error("Please ensure that you provide all command line arguments at run time or use the mutator methods to set all available calculable variables before use or programmatic consumption, respectively.");
			logger.debug("This validator is only meant for evaluating string-based inputs.  If you do not need to accept string input, please use the available integer mutator methods and constructors to use the dice roller calculator fascets.");
			System.exit(0);
		} catch (Exception e) {
			logger.error("Please ensure that you provide all command line arguments at run time or use the mutator methods to set all available calculable variables before use or programmatic consumption, respectively.");
			logger.debug("This validator is only meant for evaluating string-based inputs.  If you do not need to accept string input, please use the available integer mutator methods and constructors to use the dice roller calculator fascets.");
			System.exit(0);
		}
	}

	/**
	 * Mutator method to modulate the number of dice to be rolled; be sure for
	 * string inputs that you programmatically run the inputs against the
	 * argument validator in this package first.
	 * 
	 * @param diceNumber
	 *            The number of dice to be rolled
	 */
	public void setDiceNumber(String diceNumber) {
		this.diceNumber = Math.abs(Integer.parseInt(diceNumber));
	}

	/**
	 * Mutator method to modulate the number of dice to be rolled
	 * 
	 * @param diceNumber
	 *            The number of dice to be rolled
	 */
	public void setDiceNumber(int diceNumber) {
		this.diceNumber = Math.abs(diceNumber);
	}

	/**
	 * Mutator method to modulate the number of faces a die will have when; be
	 * sure for string inputs that you programmatically run the inputs against
	 * the argument validator in this package first. rolled
	 * 
	 * @param diceNumber
	 *            The number of faces a die will have when rolled
	 */
	public void setDiceType(String diceType) {
		this.diceType = Math.abs(Integer.parseInt(diceType));
	}

	/**
	 * Mutator method to modulate the number of faces a die will have when
	 * rolled
	 * 
	 * @param diceNumber
	 *            The number of faces a die will have when rolled
	 */
	public void setDiceType(int diceType) {
		this.diceType = Math.abs(diceType);
	}

	/**
	 * Mutator method to modulate the value by which dice rolls will be
	 * modified. This can be a positive or negative integer.
	 * 
	 * Be sure for string inputs that you programmatically run the inputs
	 * against the argument validator in this package first.
	 * 
	 * @param diceNumber
	 *            The value by which dice rolls will be modified
	 */
	public void setDiceModifier(String diceModifier) {
		this.diceModifier = Integer.parseInt(diceModifier);
	}

	/**
	 * Mutator method to modulate the value by which dice rolls will be
	 * modified. This can be a positive or negative integer.
	 * 
	 * @param diceNumber
	 *            The value by which dice rolls will be modified
	 */
	public void setDiceModifier(int diceModifier) {
		this.diceModifier = diceModifier;
	}

	/**
	 * Accessor method to retrieve the number of faces a die will have when
	 * rolled
	 * 
	 * @param diceNumber
	 *            The number of faces a die will have when rolled
	 */
	public int getDiceNumber() {
		return this.diceNumber;
	}

	/**
	 * Accessor method to retrieve the number of faces a die will have when
	 * rolled
	 * 
	 * @param diceNumber
	 *            The number of faces a die will have when rolled
	 */
	public int getDiceType() {
		return this.diceType;
	}

	/**
	 * Accessor method to retrieve the value by which dice rolls will be
	 * modified
	 * 
	 * @param diceModifier
	 *            The value by which dice rolls will be modified
	 */
	public int getDiceModifier() {
		return this.diceModifier;
	}

	/**
	 * Accessor method to retrieve the value of the last run die roll on the
	 * current thread. This should only be access for reference of a last run
	 * roll after a value has been assess via the direct-access methods in this
	 * class, such as for testing purposes.
	 * 
	 * @return The last run die roll on this thread
	 */
	public int getCurrentRoll() {
		return this.currentRoll;
	}

	/**
	 * Calculator that determines the pseudo-randomized dice rolls based on dice
	 * inputs. All mutatable variables related to dice rolls must be set in
	 * order to run this method.
	 * 
	 * <em>Note:</em> Modifiers to dice rolls are applied only once, after all
	 * rolls are summed.
	 * 
	 * @return The sum of all consecutive dice rolls, with modifiers applied
	 *         only once after all rolls are summed. This will also output to
	 *         the command line all single die rolls as they occur.
	 */
	public int runTotalDiceRoller() {
		logger.info("Calculating sum of dice rolls...");

		for (int i = 0; i < getDiceNumber(); i++) {
			Random rn = new Random();
			this.currentRoll = rn.nextInt(getDiceType()) + 1;
			logger.info("[" + i + "] " + this.currentRoll);

			this.totalDiceRoll += this.currentRoll;
		}
		logger.info("Modifier being applied: " + getDiceModifier());

		return this.totalDiceRoll + getDiceModifier();
	}

	/**
	 * Calculator that determines the pseudo-randomized dice roll based on dice
	 * inputs for one and only one roll at a time. Therefore, this method will
	 * disregard the number of dice input via mutator methods, and only requires
	 * the number of faces on a die (i.e., the type of die) in order to run.
	 * 
	 * <em>Note:</em> This applies any specified modifiers only once to the roll
	 * specified.
	 * 
	 * @return A single pseudo-randomized die roll with any specified modifiers
	 *         applied.
	 */
	public int runCurrentDiceRoller() {
		logger.info("Calculating one of die roll...");

		Random rn = new Random();

		this.currentRoll = rn.nextInt(getDiceType()) + 1;
		logger.info("1d" + getDiceType() + "+[" + getDiceModifier() + "]: "
				+ this.currentRoll + "+[" + getDiceModifier() + "]");

		return this.currentRoll + getDiceModifier();
	}

	/**
	 * Simple dice roll printer. This will print dice roll total results with
	 * any specified modifiers and use-selected dice to command line.
	 */
	public void printDiceRoller() {
		logger.info("\nTOTAL DICE ROLL:\n" + getDiceNumber() + "d"
				+ getDiceType() + "+[" + getDiceModifier() + "]" + "\nTotal: "
				+ runTotalDiceRoller());
	}
}
