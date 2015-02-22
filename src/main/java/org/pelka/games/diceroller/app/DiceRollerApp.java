package org.pelka.games.diceroller.app;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.pelka.games.diceroller.calc.DiceRoller;

/**
 * Main orchestration for the dice roller application.
 * 
 * @author arnoldpelka
 *
 */
public class DiceRollerApp {
	private final static Logger logger = Logger.getLogger(DiceRollerApp.class);

	/**
	 * The main method for the dice roller application and orchestrator for the
	 * remainder of the application.
	 * 
	 * @param args
	 *            Command line arguments: The first number to be entered will
	 *            indicate the number of dice to roll; The second number will
	 *            the size dice to be rolled.
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		logger.info("\n|****************************************************|\n|**    RUNNING ORCHESTRATION OF DICE ROLLER APP    **|\n|****************************************************|");
		DiceRoller dr = new DiceRoller();

		dr.validateArgs(args);
		dr.setDiceNumber(args[0]);
		dr.setDiceType(args[1]);
		if (args.length > 2) {
			dr.setDiceModifier(args[2]);
		}

		dr.printDiceRoller();
	}

}
