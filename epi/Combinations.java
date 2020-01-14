package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.LexicographicalListComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
public class Combinations {
  @EpiTest(testDataFile = "combinations.tsv")

  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> result=new ArrayList();
    List set=List.of(0,1);

    subset(result,new LinkedList<>(),set,k,0);
   return result;
  }
  public static void subset(List<List<Integer>> result,List<Integer> partial,List<Integer> set,int k,int offset){

    result.add(new ArrayList<>(partial));

    for(int i=offset;i<set.size();i++){
      ((LinkedList<Integer>) partial).addLast(set.get(i));
      subset(result,partial,set,k,i+1);
      ((LinkedList<Integer>) partial).removeLast();
    }
  }
  @EpiTestComparator
  public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
      (expected, result) -> {
    if (result == null) {
      return false;
    }
    expected.sort(new LexicographicalListComparator<>());
    result.sort(new LexicographicalListComparator<>());
    return expected.equals(result);
  };

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Combinations.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
