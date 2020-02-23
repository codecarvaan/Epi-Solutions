package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixEnclosedRegions {

    public static void fillSurroundedRegions(List<List<Character>> board) {
        List<List<Boolean>> visited = new ArrayList<>();
        for (int i = 0; i < board.size(); ++i) {
            visited.add(new ArrayList(Collections.nCopies(board.get(i).size(), false)));
        }

        //row=0
        for (int i = 0; i < board.get(0).size(); i++) {
            helper(board, visited, 0, i);
        }

        //row=board.size()
        for (int i = 0; i < board.get(0).size(); i++) {
            helper(board, visited, board.size() - 1, i);
        }
        // col=0
        for (int i = 0; i < board.size(); i++) {
            helper(board, visited, i, 0);
        }
        //col size
        for (int i = 0; i < board.size(); i++) {
            helper(board, visited, i, board.get(i).size() - 1);
        }

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == 'W' && visited.get(i).get(j) == false) {
                    board.get(i).set(j, 'B');
                }
            }
        }
        return;
    }

    public static void helper(List<List<Character>> board, List<List<Boolean>> visited, int i, int j) {

        if (i < 0 || i >= board.size() || j < 0 || j >= board.get(i).size() || board.get(i).get(j) == 'B' || visited.get(i).get(j) == true) {
            return;
        }
        visited.get(i).set(j, true);

        List<List<Integer>> moves = new ArrayList<>();
        moves.add(List.of(0, 1));
        moves.add(List.of(1, 0));
        moves.add(List.of(0, -1));
        moves.add(List.of(-1, 0));

        for (List<Integer> m : moves) {
            helper(board, visited, i + m.get(0), j + m.get(1));
        }
        return;
    }

    @EpiTest(testDataFile = "matrix_enclosed_regions.tsv")
    public static List<List<Character>>
    fillSurroundedRegionsWrapper(List<List<Character>> board) {
        fillSurroundedRegions(board);
        return board;
    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MatrixEnclosedRegions.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
