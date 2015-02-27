package org.pelka.tools.util.io;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.log4j.Logger;
import org.pelka.tools.actions.Attack;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * A class representing a standard XML rpgrp file like those exported from
 * PCGen. Allows the reading and fetching of specific character data from the
 * file.
 * 
 * @author Justin Mollenauer
 *
 */
public class RPGRPFileReader {
	private final static Logger logger = Logger.getLogger(RPGRPFileReader.class);
	public static boolean TEST_MODE = false;
	protected String filename;
	protected Document document;

	/**
	 * Constructor for instantiating the object given the desired rpgrp file
	 * 
	 * @param filename
	 *            File location
	 */
	public RPGRPFileReader(String filename) {
		this.filename = filename;
		init();
	}

	/**
	 * Initializes the object using the given filename. Runs once after calling
	 * constructor.
	 */
	private void init() {
		try {
			InputStream fXmlFile = null;
			ClassLoader classLoader = getClass().getClassLoader();
			
			File jarPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
	        String supportFilePath = jarPath.getParentFile().getAbsolutePath();
			
			if (TEST_MODE) {
				fXmlFile = classLoader.getResourceAsStream(filename);
			} else {
				fXmlFile = new FileInputStream(supportFilePath + "/" + filename);
			}
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			document = doc;
			
			//fXmlFile.close();
			logger.info("File opened successfully");

		} catch (FileNotFoundException fnfe) {
			logger.error("The support file specified did not exist in the given path");
		} catch (IOException ioe) {
			logger.error("There was a problem reading in a support file");
		} catch (NullPointerException npe) {
			logger.error("There was no support file found with the file name listed");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while retrieving support file data\n" + e.getCause());
		}
	}

	/**
	 * Returns the character name
	 * 
	 * @return Character Name
	 */
	public String getName() {
		return getElement("name");
	}

	/**
	 * Returns the Initiative modifier
	 * 
	 * @return Initiative Modifier
	 */
	public String getInitiative() {
		return getElement("init-modifier");
	}

	/**
	 * Returns an Array of Attack objects representing the possible attacks a
	 * character can make
	 * 
	 * @return Array of Attack objects
	 */
	public Attack[] getAttacks() {
		String text = getElement("attack");
		String[] attacks = text.split("\n\\)");
		Attack[] value = new Attack[attacks.length - 1];
		String[] tempArray = new String[attacks.length - 1];
		int i = 0;
		for (String attack : attacks) {
			if (i < attacks.length - 1) {
				tempArray[i] = attack;
				i++;
			}
		}
		attacks = tempArray;
		i = 0;
		for (String attack : attacks) {
			int curIndex = 0;
			attack = attack.trim();
			String name = attack.substring(0, attack.indexOf(':'));
			String temp = attack.substring(attack.indexOf('\n') + 1,
					attack.indexOf('\n', attack.indexOf('\n') + 1));
			curIndex = attack.indexOf('\n', attack.indexOf('\n') + 1);
			int attackBonus = 1;
			if (temp.indexOf('-') > 0) {
				attackBonus = -1;
			}
			temp = temp.substring(1).trim();
			attackBonus *= Integer.parseInt(temp);
			String type = attack.substring(attack.indexOf('\n', curIndex) + 1,
					attack.indexOf('\n', attack.indexOf('\n', curIndex) + 1))
					.trim();
			curIndex = attack.indexOf('\n', attack.indexOf('\n', curIndex) + 1);
			int diceNumber = Integer.parseInt(attack.substring(
					attack.indexOf('(', attack.indexOf('\n')) + 1,
					attack.indexOf('d', attack.indexOf('\n'))).trim());
			curIndex = attack.indexOf('d', attack.indexOf('\n'));
			char typeEnd = '\n';
			if (attack.indexOf('+') > -1) {
				typeEnd = '+';
			}
			int diceType = Integer.parseInt(attack.substring(
					attack.indexOf('d', attack.indexOf('\n')) + 1,
					attack.indexOf(typeEnd, curIndex)));
			curIndex = attack.indexOf(typeEnd, curIndex);
			int damageBonus = 0;
			if (attack.indexOf('+', curIndex) > -1
					|| attack.indexOf('-', curIndex) > -1) {
				char searchChar = '+';
				if (attack.indexOf('-', curIndex) > -1) {
					damageBonus = -1;
					searchChar = '-';
				}
				if (attack.indexOf('+', curIndex) > -1) {
					damageBonus = 1;
					searchChar = '+';
				}
				if (attack.indexOf('\n', curIndex) > -1) {
					damageBonus = Integer.parseInt(attack.substring(
							attack.indexOf(searchChar, curIndex) + 1,
							attack.indexOf('\n', curIndex)));
				} else {
					damageBonus = Integer.parseInt(attack.substring(attack
							.indexOf(searchChar, curIndex) + 1));
				}
			}
			value[i] = new Attack(name, damageBonus, diceNumber, diceType,
					type, attackBonus);
			i++;
		}
		return value;
	}

	/**
	 * Returns a HashMap which can be queried for skill modifiers Unlisted
	 * skills have a modifier of 0
	 * 
	 * @return HashMap of skills with skill modifiers
	 */
	public HashMap<String, Integer> getSkills() {
		HashMap<String, Integer> value = new HashMap<String, Integer>();
		String[] skills = getElement("skills").split(";");
		int skillValue = 1;
		String skillName = "";
		for (String skill : skills) {
			char searchChar = '+';
			skillValue = 1;
			if (skill.indexOf('-') > -1) {
				searchChar = '-';
				skillValue = -1;
			}
			try {
				skillName = skill.substring(0, skill.indexOf(searchChar))
						.trim();
				skillValue *= Integer.parseInt(skill.substring(skill
						.indexOf(searchChar) + 1));
				value.put(skillName, skillValue);
			} catch (Exception e) {
				continue;
			}
		}
		return value;
	}

	/**
	 * Generic method to fetch an element from the XML file given the element
	 * name
	 * 
	 * @param elementName
	 *            The Element Name
	 * @return Text content of the element
	 */
	private String getElement(String elementName) {
		NodeList nList = document.getElementsByTagName("combatant");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node node = nList.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				return element.getElementsByTagName(elementName).item(0)
						.getTextContent();

			}
		}
		return null;
	}
}