package CovidPack;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Queue;

public class CovidTest {

	//Holds all of the subjects
	LinkedList<String> subjects = new LinkedList<String>();

	//A linked list queue used to hold the testing line
	Queue<String> testingLine = new LinkedList<String>();

	//A linked list queue used to hold the quarantine
	Queue<Object> quarantine = new LinkedList<Object>();

	//String to hold the alphabet for the random 5 letter string
	static final String AB = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * This method uses the java SecureRandom library.
	 * This is used to randomly build a string the length of 5
	 * that represents the subjects until there are 10 subjects.
	 * @param length
	 */
	public void createSubjects(int length) {
		SecureRandom rnd = new SecureRandom();

		while (subjects.size() != 10) {
			StringBuilder sb = new StringBuilder(length);
			for(int i = 0; i < length; i++) 
				sb.append(AB.charAt(rnd.nextInt(AB.length())));
			subjects.add(sb.toString());
		}

	}

	/**
	 * This method launches the create subjects method with
	 * the length of 5. Then adds all the subjects onto the testing line.
	 * The first loop tests all the subjects one by one using a random number
	 * and using that as the percent rate that tests are positive.
	 * There is a short waiting period using the waitingTime method and
	 * then it decides whether they test positive or negative which either
	 * moves them back to the back of the line or into the quarantine queue.
	 * Once the testing line is empty, we move on the the quarantined subjects.
	 * There is a short waiting period in between each which simulates a 
	 * passing of time (14 days). After quarantine is empty, COVID-19
	 * is officially gone.
	 */
	public void testCovid() {

		createSubjects(5);
		testingLine.addAll(subjects);
		System.out.println("Testing Line: " + testingLine);
		System.out.println();

		while (testingLine.isEmpty() == false) {
			double rand = Math.random();
			System.out.print("Processing Subject " + testingLine.peek());
			waitingTime(3.75);

			if (rand < .1) {
				System.out.println(testingLine.peek() + " has tested positive for COVID-19.");
				System.out.println(testingLine.peek() + " will now be quarantined for 14 days.");
				quarantine.add(testingLine.poll());
			}
			else {
				System.out.println(testingLine.peek() + " has tested negative for COVID-19.");
				System.out.println(testingLine.peek() + " will be moved to the back of the line.");
				testingLine.add(testingLine.poll());
			}

			System.out.println();
			System.out.println("Testing Line: " + testingLine);
			System.out.println("Quarantined: " + quarantine);
			System.out.println();
		}

		System.out.println("Everyone has been quarantined!");
		while (quarantine.isEmpty() == false) {
			System.out.println();
			System.out.println("Quarantined: " + quarantine);
			System.out.print("Time passes");
			waitingTime(7.5);
			System.out.println(quarantine.poll() + " has been freed!");
		}

		System.out.println();
		System.out.println("COVID-19 is officially gone!");
	}

	/**
	 * This method simulates the waiting of a certain amount of time depending
	 * on the parameter. It uses the Thread.sleep() method which just stalls
	 * for one second until the loop requirements are met. This is all surrounded
	 * by a try catch as the Thread.sleep method should be.
	 * @param time
	 */
	public void waitingTime(double time) {

		try {
			for (double i=0; i<time ; i++) {
				Thread.sleep(1000);
				System.out.print(".");
			}
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		System.out.println();
	}
}
