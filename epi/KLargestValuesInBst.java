package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class KLargestValuesInBst {
    @EpiTest(testDataFile = "k_largest_values_in_bst.tsv")


    public static List<Integer> findKLargestInBst(BstNode<Integer> tree, int k) {

        List<Integer> list = new ArrayList();
        util(tree, k, list);
        return list;
    }

    public static void util(BstNode<Integer> tree, int k, List<Integer> list) {
        if (tree == null) {
            return;
        }
        if (list.size() == k) {
            return;
        }
        util(tree.right, k, list);
        if (list.size() < k) {
            list.add(tree.data);
        }
        util(tree.left, k, list);
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KLargestValuesInBst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
