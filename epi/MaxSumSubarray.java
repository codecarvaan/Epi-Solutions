package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class MaxSumSubarray {
  @EpiTest(testDataFile = "max_sum_subarray.tsv")

  public static int findMaximumSubarray(List<Integer> A) {

    int maxSum=0;
    Integer sum = 0;
    for(Integer a:A){
      sum=sum+a;
      if(sum<0){
        sum=0;
      }
      if(sum>maxSum){
        maxSum=sum;
      }
    }
    return maxSum;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MaxSumSubarray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
