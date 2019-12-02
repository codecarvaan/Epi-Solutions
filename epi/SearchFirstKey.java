package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
public class SearchFirstKey {
  @EpiTest(testDataFile = "search_first_key.tsv")

  public static int searchFirstOfK(List<Integer> A, int k) {

    int left=0;
    int right=A.size()-1;
    int pos=A.size();
    while(left<=right){
      int mid=left+(right-left)/2;
      if(A.get(mid)==k && pos>mid){
          pos=mid;
          right=mid-1;
      }else if(A.get(mid)>k){
        right=mid-1;
      }else{
        left=mid+1;
      }
    }
    return pos==A.size()?-1:pos;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchFirstKey.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
