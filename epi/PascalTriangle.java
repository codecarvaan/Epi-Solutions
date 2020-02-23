package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class PascalTriangle {
  @EpiTest(testDataFile = "pascal_triangle.tsv")

  public static List<List<Integer>> generatePascalTriangle(int numRows) {
      List<List<Integer>> ans = new ArrayList<>();
      for (int i = 0; i < numRows; i++) {
          List<Integer> part = new ArrayList<>();
          for (int j = 0; j <= i; j++) {
              part.add(j < i && j - 1 >= 0 ? ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1) : 1);
          }
          ans.add(part);
      }

      return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PascalTriangle.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
