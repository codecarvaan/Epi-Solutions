package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MinimumDistance3SortedArrays {

  public static class ArrayData implements Comparable<ArrayData> {
    public int val;
    public int idx;

    public ArrayData(int idx, int val) {
      this.val = val;
      this.idx = idx;
    }

    @Override
    public int compareTo(ArrayData o) {
      int result = Integer.compare(val, o.val);
      if (result == 0) {
        result = Integer.compare(idx, o.idx);
      }
      return result;
    }
  }

  @EpiTest(testDataFile = "minimum_distance_3_sorted_arrays.tsv")

  public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
    int heads[] = new int[sortedArrays.size()];
    for (int i = 0; i < heads.length; i++) {
      heads[i] = 0;
    }
    NavigableSet<ArrayData> currentHeads = new TreeSet<>();
    for (int i = 0; i < heads.length; i++) {
      currentHeads.add(new ArrayData(i, sortedArrays.get(i).get(0)));
    }
    int minDistanceSoFar = Integer.MAX_VALUE;
    while (true) {
      minDistanceSoFar = Math.min(minDistanceSoFar, currentHeads.last().val - currentHeads.first().val);
      int idx = currentHeads.first().idx;
      heads[idx] = heads[idx] + 1;
      if (sortedArrays.get(idx).size() <= heads[idx]) {
        break;
      }
      currentHeads.pollFirst();
      currentHeads.add(new ArrayData(idx, sortedArrays.get(idx).get(heads[idx])));
    }
    return minDistanceSoFar;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "MinimumDistance3SortedArrays.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
