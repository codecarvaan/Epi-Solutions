package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.PriorityQueue;


public class  KthLargestElementInLongArray {

  private static int findKthLargestUnknownLength(Iterator<Integer> stream,
                                                 int k) {
    PriorityQueue<Integer> heap=new PriorityQueue<>();
    int count=0;
    while(stream.hasNext()){
      heap.add(stream.next());
      count++;
      if(count>k){
        heap.poll();
        count--;
      }
    }


    final Integer poll = heap.poll();
    return poll;
  }
  @EpiTest(testDataFile = "kth_largest_element_in_long_array.tsv")
  public static int findKthLargestUnknownLengthWrapper(List<Integer> stream,
                                                       int k) {
    return findKthLargestUnknownLength(stream.iterator(), k);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "KthLargestElementInLongArray.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
