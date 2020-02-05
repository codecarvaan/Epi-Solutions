package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.List;

public class ThreeSum {
    @EpiTest(testDataFile = "three_sum.tsv")

    public static boolean hasThreeSum(List<Integer> A, int t) {
        // TODO - you fill in here.
        Collections.sort(A);
        return A.stream().anyMatch(a -> TwoSum.hasTwoSum(A, t - a));
    }

    public static boolean hasTwoSum(List<Integer> A, int start, int end, int t) {  //without repetition
        while (start < end) {
            if (A.get(start) + A.get(end) == t) {
                return true;
            }
            if (A.get(start) + A.get(end) < t) {
                start++;
            } else {
                end--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ThreeSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
