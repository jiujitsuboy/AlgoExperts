package training.others;

import java.util.ArrayList;
import java.util.List;

public class CelebrityProblem {
	 

	public static void main(String[] args) {
		int[][] personsAtParty = new int[][]
//		{ { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 } };
		{ { 0, 1, 1, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 0 } };

		int celebrity = getCelebrity(personsAtParty);
		if (celebrity < 0) {
			System.out.println("There is no celebrity at the party");
		} else {
			System.out.println(String.format("The celebrity is: %d", celebrity));
		}
	}

	// O(n)
	private static int getCelebrity(int[][] personsAtParty) {
		List<Integer> possibleCelebrity = new ArrayList<Integer>();

		for (int person = 0; person < personsAtParty.length; person++) {
			if (possibleCelebrity.size() > 0) {

				int indexPotential = -1;
				boolean hasAcquaintance = false;
				for (int possible = possibleCelebrity.size() - 1; possible >= 0; possible--) {

					int knows = possibleCelebrity.get(possible);

					if (person != knows) {
						
						hasAcquaintance = haveAcquaintance(person, knows, personsAtParty);
						
						if (!hasAcquaintance) {
							possibleCelebrity.remove(possible);
						}						
					}
					else {
						indexPotential = possible;
					}

				}
				
				if(hasAcquaintance && indexPotential!=-1) {
					possibleCelebrity.remove(indexPotential);
				}
				
			} else {
				for (int knows = person + 1; knows < personsAtParty.length; knows++) {
					if (haveAcquaintance(person, knows, personsAtParty)
							&& !haveAcquaintance(knows, person, personsAtParty)) {
						possibleCelebrity.add(knows);
					}
				}
			}

		}

		return possibleCelebrity.size() > 0 ? possibleCelebrity.get(0) : -1;
	}

	private static boolean haveAcquaintance(int person, int knows, int[][] personsAtParty) {
		return (personsAtParty[person][knows] == 1);
	}
}
