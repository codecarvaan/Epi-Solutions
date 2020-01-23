package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class NumberOfScoreCombinations {
    @EpiTest(testDataFile = "number_of_score_combinations.tsv")

    public static int numCombinationsForFinalScore(int finalScore, List<Integer> individualPlayScores) {

        int[] cache = new int[finalScore + 5];
        cache[0] = 1;
        for (int i = 0; i < individualPlayScores.size(); i++) {
            if (finalScore >= individualPlayScores.get(i)) {
                cache[individualPlayScores.get(i)] = cache[individualPlayScores.get(i)] + 1;
            }
            for (int j = individualPlayScores.get(i) + 1; j <= finalScore; j++) {
                cache[j] = cache[j] + cache[j - individualPlayScores.get(i)];
            }
        }
        return cache[finalScore];
    }


    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfScoreCombinations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}