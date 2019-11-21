package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class SearchShiftedSortedArray {
  @EpiTest(testDataFile = "search_shifted_sorted_array.tsv")
//trick is that smallest index will be always in left
  public static int searchSmallest(List<Integer> A) {
  int left=0;
  int right=A.size()-1;
  int mid;

  while(left<right){
    mid=left+(right-left)/2;

    if(A.get(mid)>A.get(right)){
      left=mid+1;
    }else{
      right=mid;
    }

  }
    return left;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SearchShiftedSortedArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
