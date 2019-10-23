package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PathSum {
  @EpiTest(testDataFile = "path_sum.tsv")

  public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                   int remainingWeight) {

    return hasSumHandler(tree,remainingWeight,0);
  }

  public static boolean hasSumHandler(BinaryTreeNode<Integer> tree,int weight,int sum){

    if(tree==null){
      return false;
    }
    sum=sum+tree.data;
    if(sum==weight && tree.right==null && tree.left==null){
      return true;
    }
    return hasSumHandler(tree.left,weight,sum)||hasSumHandler(tree.right, weight, sum);

  }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PathSum.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
