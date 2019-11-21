package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {
//Search first of K in sorted List in O(Log(n)) Time complexity :-Binary search
    int left=0;
    int right=A.size()-1;
    int mid;
    int latestIndex=-1;
    while(left<=right ){
      mid=left+(right-left)/2;
      if(A.get(mid)==k){
        latestIndex=mid;
        right=mid-1;
      }
      else if(A.get(mid)<k){
        left=mid+1;
      }
      else{
        right=mid-1;
      }
    }


    return latestIndex;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
