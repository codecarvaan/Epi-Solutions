package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreePostorder {
  @EpiTest(testDataFile = "tree_postorder.tsv")

  // We use stack and previous node pointer to simulate postorder traversal.
  public static List<Integer> postorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> ls=new ArrayList();
//    postorder(tree,ls);
    PostorderWithIteration(tree,ls);
    return ls;
  }
  static void PostorderWithIteration(BinaryTreeNode<Integer> root,List<Integer> ls){
    //TODO
    Stack<BinaryTreeNode> stack = new Stack();


    while(!stack.isEmpty() || root!=null){

      while(root!=null){
        stack.push(root);

        root=root.left;
      }
      BinaryTreeNode node=stack.pop();
      ls.add((Integer) node.data);
      root=node.right;

    }
  }
  static  void postorder(BinaryTreeNode<Integer> tree,List<Integer> ls){
    if(tree!=null){
      postorder(tree.left,ls);

      postorder(tree.right,ls);
      ls.add(tree.data);
    }
  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePostorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
