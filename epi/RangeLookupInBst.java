package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class RangeLookupInBst {
    @EpiTest(testDataFile = "range_lookup_in_bst.tsv")

    public static List<Integer> rangeLookupInBst(BstNode<Integer> tree,
                                                 Interval interval) {

        List<Integer> result = new ArrayList<>();
        rangeLookupInBstHelper(tree, interval, result);
        return result;
    }

    public static void rangeLookupInBstHelper(BstNode<Integer> tree,
                                              Interval interval,
                                              List<Integer> result) {
        if (tree == null) {
            return;
        }
        if (tree.data >= interval.left && tree.data <= interval.right) {
            rangeLookupInBstHelper(tree.left, interval, result);
            result.add(tree.data);
            rangeLookupInBstHelper(tree.right, interval, result);
        } else if (tree.data < interval.left) {
            rangeLookupInBstHelper(tree.right, interval, result);
        } else if (tree.data > interval.right) {
            rangeLookupInBstHelper(tree.left, interval, result);
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "RangeLookupInBst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
