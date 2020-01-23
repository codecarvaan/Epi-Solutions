package epi;

import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

public class DescendantAndAncestorInBst {

    static int flag;

    public static boolean
    pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
                                         BstNode<Integer> possibleAncOrDesc1,
                                         BstNode<Integer> middle) {
        BstNode<Integer> search0 = possibleAncOrDesc0;
        BstNode<Integer> search1 = possibleAncOrDesc1;
        while ((search0 != null || search1 != null) && search0 != middle && search1 != middle && search0 != possibleAncOrDesc1 && search1 != possibleAncOrDesc0) {
            if (search0 != null) {
                search0 = search0.data > middle.data ? search0.left : search0.right;
            }
            if (search1 != null) {
                search1 = search1.data > middle.data ? search1.left : search1.right;
            }
        }
        if ((search0 != middle && search1 != middle) || search0 == middle && search1 == middle) {
            return false;
        }
        return search0 == middle ? util(middle, possibleAncOrDesc1) : util(middle, possibleAncOrDesc0);
    }

    public static boolean util(BstNode<Integer> source, BstNode<Integer> target) {
        if (source == null) {
            return false;
        }
        if (source.data > target.data) {
            return util(source.left, target);
        } else if (source.data < target.data) {
            return util(source.right, target);
        } else {
            return true;
        }
    }

    @EpiTest(testDataFile = "descendant_and_ancestor_in_bst.tsv")
    public static boolean pairIncludesAncestorAndDescendantOfMWrapper(
            TimedExecutor executor, BstNode<Integer> tree, int possibleAncOrDesc0,
            int possibleAncOrDesc1, int middle) throws Exception {
        final BstNode<Integer> candidate0 =
                BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc0);
        final BstNode<Integer> candidate1 =
                BinaryTreeUtils.mustFindNode(tree, possibleAncOrDesc1);
        final BstNode<Integer> middleNode =
                BinaryTreeUtils.mustFindNode(tree, middle);

        return executor.run(()
                -> pairIncludesAncestorAndDescendantOfM(
                candidate0, candidate1, middleNode));
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "DescendantAndAncestorInBst.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
