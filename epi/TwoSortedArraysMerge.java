package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class TwoSortedArraysMerge {

  public static void mergeTwoSortedArrays(List<Integer> A, int m,
                                          List<Integer> B, int n) {
    int end=m+n-1;
    int AIndex=m-1;
    int BIndex=n-1;
    while(end>=0 && AIndex>=0 &&BIndex>=0){
      if(A.get(AIndex)>B.get(BIndex)){
        A.set(end--,A.get(AIndex--));
      }else{
        A.set(end--,B.get(BIndex--));
      }
    }
    while(BIndex>=0){
      A.set(end--,B.get(BIndex--));
    }

    return;
  }
  @EpiTest(testDataFile = "two_sorted_arrays_merge.tsv")
  public static List<Integer>
  mergeTwoSortedArraysWrapper(List<Integer> A, int m, List<Integer> B, int n) {
    mergeTwoSortedArrays(A, m, B, n);
    return A;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TwoSortedArraysMerge.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
