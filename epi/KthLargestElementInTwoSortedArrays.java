package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementInTwoSortedArrays {
  @EpiTest(testDataFile = "kth_largest_element_in_two_sorted_arrays.tsv")

  public static int findKthNTwoSortedArrays(List<Integer> A, List<Integer> B, int k) {
    PriorityQueue<Integer> heap=new PriorityQueue<>();

    int i=0,j=0;
    int limit=0;
    ArrayList<Integer> list=new ArrayList();
    while(i<A.size()||j<B.size()){
      if(i<A.size() && j<B.size()) {
        if (A.get(i) < B.get(j)) {
          list.add(A.get(i));
          i++;

        } else {
          list.add(B.get(j));
          j++;

        }
      }
      else if(i<A.size()){
        list.add(A.get(i));
        i++;
      }
      else{
        list.add(B.get(j));
        j++;
      }
      limit++;
    }
    return list.get(k-1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestElementInTwoSortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
