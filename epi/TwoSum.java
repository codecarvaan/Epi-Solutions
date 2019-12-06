package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class TwoSum {
    @EpiTest(testDataFile = "two_sum.tsv")

    public static boolean hasTwoSum(List<Integer> A, int t) {

        int start = 0;
        int end = A.size() - 1;

        while (start <= end) {
            int sum = A.get(start) + A.get(end);

            if (sum == t) {
                return true;
            } else if (sum > t) {
                end--;
            } else {
                start++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "TwoSum.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
