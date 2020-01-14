package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
public class LongestSubarrayWithDistinctValues {
  @EpiTest(testDataFile = "longest_subarray_with_distinct_values.tsv")

  public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
    int start=0;
    int max=0;
    HashMap<Integer,Integer> map=new HashMap<>();
    for(int i=0;i<A.size();i++){
      Integer prev= map.put(A.get(i),i);

      if(prev!=null){
        if(prev>=start){
          max=Math.max(max,i-start);
          start=prev+1;
        }
      }
    }
    return Math.max(max,A.size()-start);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LongestSubarrayWithDistinctValues.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
