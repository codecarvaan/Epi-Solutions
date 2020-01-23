package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Arrays;

public class NumberOfTraversalsMatrix {
    @EpiTest(testDataFile = "number_of_traversals_matrix.tsv")

    public static int numberOfWays(int n, int m) {
        int arr[][] = new int[n][m];
        for (int rows[] : arr) {
            Arrays.fill(rows, -1);
        }
        return util(n - 1, m - 1, arr);
    }

    public static int util(int n, int m, int arr[][]) {
        if (n == 0 && m == 0) {
            return 1;
        }
        if (arr[n][m] == -1) {
            int down = m == 0 ? 0 : util(n, m - 1, arr);
            int right = n == 0 ? 0 : util(n - 1, m, arr);
            arr[n][m] = down + right;
        }
        return arr[n][m];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsMatrix.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
