package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class PrettyPrinting {
    @EpiTest(testDataFile = "pretty_printing.tsv")

    public static int minimumMessiness(List<String> words, int lineLength) {
        // TODO - you fill in here.
        words = List.of("a", "b", "c", "d");
        lineLength = 5;
        //expected 8
        int minimumMessiness[] = new int[words.size()];
        Arrays.fill(minimumMessiness, Integer.MAX_VALUE);

        int numRemainingBlanks = words.get(0).length() - lineLength;
        minimumMessiness[0] = numRemainingBlanks * numRemainingBlanks;  //placing word 0 to i+1 word

        for (int i = 1; i < words.size(); i++) {
            numRemainingBlanks = lineLength - words.get(i).length();
            minimumMessiness[i] = minimumMessiness[i - 1] + numRemainingBlanks * numRemainingBlanks;

            for (int j = i - 1; j >= 0; j--) {
                numRemainingBlanks = numRemainingBlanks - words.get(j).length() - 1;
                if (numRemainingBlanks < 0) {
                    break;
                }
                int firstJMessiness = j - 1 < 0 ? 0 : minimumMessiness[j - 1]; //doubt
                int currentLineMessiness = numRemainingBlanks * numRemainingBlanks;
                minimumMessiness[i] = Math.min(minimumMessiness[i], firstJMessiness + currentLineMessiness);
            }


        }
        return minimumMessiness[words.size() - 1];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PrettyPrinting.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
