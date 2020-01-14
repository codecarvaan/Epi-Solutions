package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class NQueens {
    @EpiTest(testDataFile = "n_queens.tsv")

    public static List<List<Integer>> nQueens(int n) {
        // TODO - you fill in here.
        List<List<Integer>> result = new ArrayList<>();
        totalWays(n, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public static void totalWays(int n, int row, List<Integer> column, List<List<Integer>> result) {
        if (row == n) {
            result.add(new ArrayList<>(column));
        } else {
            for (int i = 0; i < n; i++) {
                column.add(i);
                if (validColumn(column)) {
                    totalWays(n, row + 1, column, result);
                }
                column.remove(column.size() - 1);
            }
        }
    }

    public static boolean validColumn(List<Integer> column) {
        int rowID = column.size() - 1;
        for (int i = 0; i < rowID; ++i) {
            int diff = Math.abs(column.get(i) - column.get(rowID));
            if (diff == 0 || diff == rowID - i) {
                return false;
            }
        }
        return true;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                expected.sort(new LexicographicalListComparator<>());
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NQueens.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
