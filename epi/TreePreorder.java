package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreePreorder {
  @EpiTest(testDataFile = "tree_preorder.tsv")

  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    // TODO - you fill in here.
    List<Integer> ls=new ArrayList();
    preorder(tree,ls);
    return ls;
  }
  static  void preorder(BinaryTreeNode<Integer> tree,List<Integer> ls){
    if(tree!=null){
      ls.add(tree.data);
      preorder(tree.left,ls);
      preorder(tree.right,ls);

    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
