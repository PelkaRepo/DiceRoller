# DiceRoller
This is a simple Java application that runs at command line to perform simple dice rolling calculations for any number of one type of die.  This program is meant to be run once per roll before returning from the JVM.

Currently built, run and tested using Java 1.8 JDK

##### Build
This application is built using Maven and packages binary contents in a standard jar with dependencies executable.

##### Run
Simply use <em>java -jar DiceRoller.jar (number of dice) (type of die)</em> to run at command line, where the arguments would be <em>1 20</em> to represent 1 roll of a d20 die, for example.

##### Future Development
The current build of the dice rolling calculations package is built in a way that could be accessed in a thread-safe manner, ultimately to be used through a Spring MVC or Springboot service, and could be theoretically consumed by a dynamic, AngularJS-based UI to be shared to a group of players.

Future iterations will be built toward this end.
