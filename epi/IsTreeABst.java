package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IsTreeABst {
  @EpiTest(testDataFile = "is_tree_a_bst.tsv")

  public static boolean helper(BinaryTreeNode<Integer> root,Integer min,Integer max){
  // TODO
    if(root==null){
     return true;
   }
   if(root.data<min && min!=null){
     return false;
   }
   if(root.data>max && max!=null){
     return false;
   }
   return true;
  }
  public static boolean isBinaryTreeBST(BinaryTreeNode<Integer> tree) {
    return  helper(tree, null, null);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeABst.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
