package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class LevenshteinDistance {
    @EpiTest(testDataFile = "levenshtein_distance.tsv")

    public static int levenshteinDistance(String A, String B) {
        // TODO - you fill in here.
        int arr[][] = new int[A.length()][B.length()];
        for (int[] row : arr) {
            Arrays.fill(row, -1);
        }
        int ans = util(A, A.length() - 1, B, B.length() - 1, arr);
        return ans;
    }

    public static int util(String A, int Ai, String B, int Bi, int cache[][]) {

        if (Ai < 0) {
            return Bi + 1;
        } else if (Bi < 0) {
            return Ai + 1;
        }

        if (cache[Ai][Bi] == -1) {
            if (A.charAt(Ai) == B.charAt(Bi)) {
                cache[Ai][Bi] = util(A, Ai - 1, B, Bi - 1, cache);
            } else {
                cache[Ai][Bi] = 1 + Math.min(Math.min(util(A, Ai, B, Bi - 1, cache), util(A, Ai - 1, B, Bi, cache)), util(A, Ai - 1, B, Bi - 1, cache));
            }
        }
        return cache[Ai][Bi];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LevenshteinDistance.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
