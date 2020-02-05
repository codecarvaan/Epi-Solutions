package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;

public class IsStringInMatrix {

    @EpiTest(testDataFile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid, List<Integer> pattern) {

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (patternUtil(grid, pattern, i, j, 0, new HashMap<String, Boolean>()))
                    return true;
            }
        }
        return false;
    }

    public static boolean patternUtil(List<List<Integer>> grid, List<Integer> pattern, int i, int j, int level, HashMap<String, Boolean> previosAttempt) {

        if (pattern.size() == level)
            return true;
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid.get(0).size() || previosAttempt.containsKey(i + "#" + j + "#" + level))
            return false;

        if (pattern.get(level) == grid.get(i).get(j)) {
//          int temp=grid.get(i).get(j);  if cell cannot be included twice
//            grid.get(i).set(j,0);
            boolean check = patternUtil(grid, pattern, i + 1, j, level + 1, new HashMap<String, Boolean>()) ||
                    patternUtil(grid, pattern, i - 1, j, level + 1, new HashMap<String, Boolean>()) ||
                    patternUtil(grid, pattern, i, j + 1, level + 1, new HashMap<String, Boolean>()) ||
                    patternUtil(grid, pattern, i, j - 1, level + 1, new HashMap<String, Boolean>());
//            grid.get(i).set(j,temp);
            if (check)
                return true;
            previosAttempt.put(i + "#" + j + "#" + level, false);
        }
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringInMatrix.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
