package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class SearchUnknownLengthArray {
    @EpiTest(testDataFile = "search_unknown_length_array.tsv")

    public static int binarySearchUnknownLength(List<Integer> A, int k) {

        int p = 0;

        while (true) {
            int index = 1 << p;
            try {
                if (A.get(index) > k) {
                    break;
                } else if (A.get(index) < k) {
                    p++;
                } else {
                    return index;
                }
            } catch (Exception ex) {
                break;
            }

        }
        int left = Math.max(0, 1 << (p - 1));
        int right = 1 << p;

        while (left <= right) {
            int m = left + (right - left) / 2;
            try {
                if (A.get(m) == k) {
                    return m;
                } else if (A.get(m) < k) {
                    left = m + 1;
                } else {
                    right = m - 1;
                }
            } catch (Exception ex) {
                right = m - 1;
            }
        }


        return -1;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchUnknownLengthArray.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
