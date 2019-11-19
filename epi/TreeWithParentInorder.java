package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    ArrayList ls=new ArrayList();
    BinaryTree<Integer> curr=tree;
    BinaryTree<Integer> prev=null;
    while(curr!=null){
      if(curr.left==prev)
        if(curr.left!=null){
          tree=tree.left;
        }
        else{
          ls.add(tree.data);
          tree=tree.parent;
        }
    }
    return ls;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
