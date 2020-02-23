package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class MatrixConnectedRegions {
    public static void flipColor(int x, int y, List<List<Boolean>> image) {
        helper(x, y, image, !image.get(x).get(y));
        return;
    }

    public static void helper(int x, int y, List<List<Boolean>> image, boolean convertTo) {

        if (x >= image.size() || x < 0 || y >= image.get(x).size() || y < 0 || image.get(x).get(y) == convertTo) {
            return;
        }

        image.get(x).set(y, convertTo);
        List<List<Integer>> moves = new ArrayList<>();
        moves.add(List.of(0, 1));
        moves.add(List.of(0, -1));
        moves.add(List.of(1, 0));
        moves.add(List.of(-1, 0));

        for (List<Integer> m : moves) {
            helper(x + m.get(0), y + m.get(1), image, convertTo);
        }

    }

    @EpiTest(testDataFile = "painting.tsv")
    public static List<List<Integer>> flipColorWrapper(TimedExecutor executor,
                                                       int x, int y,
                                                       List<List<Integer>> image)
            throws Exception {
        List<List<Boolean>> B = new ArrayList<>();
        for (int i = 0; i < image.size(); i++) {
            B.add(new ArrayList<>());
            for (int j = 0; j < image.get(i).size(); j++) {
                B.get(i).add(image.get(i).get(j) == 1);
      }
    }

    executor.run(() -> flipColor(x, y, B));

    image = new ArrayList<>();
    for (int i = 0; i < B.size(); i++) {
      image.add(new ArrayList<>());
      for (int j = 0; j < B.get(i).size(); j++) {
        image.get(i).add(B.get(i).get(j) ? 1 : 0);
      }
    }

    return image;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixConnectedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
