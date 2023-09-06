package training.easy.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Having two arrays, where the first one contains the names of the teams (home/away) competing and the second one
 * containing the winner (0 = away, 1 = home). Calculate the winner of the tournament, considering that every team that
 * won a match will receive 3 points and the loser 0 points.
 */
public class TournamentWinner {
    public static void main(String[] args) {
        String[][] competitors = {{"HTML", "C#"}, {"C#", "Python"}, {"Python", "HTML"}};
        int[] results = {0, 0, 1};
        System.out.println(getTournamentWinner(competitors, results));
    }

    /**
     * time complexity: O(r), I traverse once the results array
     *
     * space complexity: using a map storing all the competitors O(c)
     *
     * @param competitors
     * @param results
     * @return
     */
    public static String getTournamentWinner(String[][] competitors, int[] results) {

        int maxValue = 0;
        String winner = null;
        Map<String, Integer> scoresByTeam = new HashMap<>(results.length);

        for (int index = 0; index < results.length; index++) {
            String homeTeam = competitors[index][0];
            String awayTeam = competitors[index][1];

            scoresByTeam.computeIfAbsent(homeTeam, value->0);
            scoresByTeam.computeIfAbsent(awayTeam, value->0);

            if(results[index]==0){
                scoresByTeam.computeIfPresent(awayTeam, (key, value)-> value + 3);
            }
            else{
                scoresByTeam.computeIfPresent(homeTeam, (key, value)-> value + 3);
            }
        }

        for(Map.Entry<String, Integer> entry : scoresByTeam.entrySet()){
            if(maxValue<entry.getValue()){
                maxValue = entry.getValue();
                winner = entry.getKey();
            }
        }

        return winner;

    }
}
