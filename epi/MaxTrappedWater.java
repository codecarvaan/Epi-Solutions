package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class MaxTrappedWater {
    @EpiTest(testDataFile = "max_trapped_water.tsv")

    public static int getMaxTrappedWater(List<Integer> heights) {
        int i = 0, j = heights.size() - 1, maxWater = 0;
        while (i < j) {
            maxWater = Math.max(maxWater, Math.min(heights.get(i), heights.get(j)) * (j - i));
            if (heights.get(i) < heights.get(j)) {
                i++;
            } else if (heights.get(i) > heights.get(j)) {
                j--;
            } else {
                i++;
                j--;
            }
        }
        return maxWater;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MaxTrappedWater.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
