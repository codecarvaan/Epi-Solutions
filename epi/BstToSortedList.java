package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.List;

public class BstToSortedList {
  static BstNode<Integer> pnode;

  public static BstNode<Integer> bstToDoublyLinkedList(BstNode<Integer> tree) {
    // TODO - you fill in here.
    pnode = null;
    util(tree);

    return pnode;
  }

  public static void util(BstNode<Integer> tree) {
    if (tree == null) {
      return;
    }
    util(tree.left);
    if (pnode == null) {
      pnode = tree;
    }
    pnode.right = tree;
    tree.left = pnode;


    System.out.println(tree.data);
    util(tree.right);
  }

  @EpiTest(testDataFile = "bst_to_sorted_list.tsv")
  public static List<Integer>
  bstToDoublyLinkedListWrapper(TimedExecutor executor, BstNode<Integer> tree)
          throws Exception {
    BstNode<Integer> list = executor.run(() -> bstToDoublyLinkedList(tree));

    if (list != null && list.left != null)
      throw new TestFailure(
              "Function must return the head of the list. Left link must be null");
    List<Integer> v = new ArrayList<>();
    while (list != null) {
      v.add(list.data);
      if (list.right != null && list.right.left != list)
        throw new RuntimeException("List is ill-formed");
      list = list.right;
    }
    return v;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BstToSortedList.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
