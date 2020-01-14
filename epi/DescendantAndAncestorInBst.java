package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
public class DescendantAndAncestorInBst {

  static int flag = 0;

  public static boolean
  pairIncludesAncestorAndDescendantOfM(BstNode<Integer> possibleAncOrDesc0,
                                       BstNode<Integer> possibleAncOrDesc1,
                                       BstNode<Integer> middle) {

    // TODO - you fill in here.
    if (possibleAncOrDesc0.data > possibleAncOrDesc1.data) {
      return util(possibleAncOrDesc0, middle, possibleAncOrDesc1);
    } else {
      return util(possibleAncOrDesc1, middle, possibleAncOrDesc0);
    }
  }

  public static boolean util(BstNode<Integer> p, BstNode<Integer> m, BstNode<Integer> c) {
    if (p == null) {
      return false;
    }
    if (p.data == m.data && flag == 0) {
      flag++;
    } else if (p.data == c.data && flag == 1) {
      flag++;
    }
    util(p.left, m, c);
    util(p.right, m, c);
    if (flag == 2) {
      return true;
    }
    return false;
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
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
