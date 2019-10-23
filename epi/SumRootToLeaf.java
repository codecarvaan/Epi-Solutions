package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SumRootToLeaf {


  public static int solution(BinaryTreeNode<Integer> tree,int partialSum){
      if(tree==null){
        return 0;
      }
      partialSum=partialSum*2+tree.data;
      if(tree.left==null && tree.right==null){
        return partialSum;
      }
      return solution(tree.left,partialSum)+solution(tree.right,partialSum);
  }
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")
  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {

    return solution(tree,0);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
