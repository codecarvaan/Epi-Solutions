package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class TreeInorder {

  @EpiTest(testDataFile = "tree_inorder.tsv")


  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {  //left root rigt
      List<Integer> ls=new ArrayList();
      Inorder(tree,ls);
    return ls;
  }
   static  void Inorder(BinaryTreeNode<Integer> tree,List<Integer> ls){
      if(tree!=null){
        Inorder(tree.left,ls);
        ls.add(tree.data);
        Inorder(tree.right,ls);
    }
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
