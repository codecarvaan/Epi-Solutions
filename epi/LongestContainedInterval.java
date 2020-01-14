package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class LongestContainedInterval {
    @EpiTest(testDataFile = "longest_contained_interval.tsv")

    public static int longestContainedRange(List<Integer> A) {

        HashSet<Integer> map = new HashSet<>();
        Iterator<Integer> itr = A.iterator();
        while (itr.hasNext()) {
            Integer i = itr.next();
            map.add(i);
        }
        int max = 0;
        int current = 0;
        while (map.size() > 0) {
            int key = map.iterator().next();
            int keyLeft = key - 1;
            int KeyRight = key + 1;
            current = 1;
            map.remove(key);
            while (map.contains(keyLeft)) {
                map.remove(keyLeft);
                keyLeft--;
                current++;
            }
            while (map.contains(KeyRight)) {
                map.remove(KeyRight);
                KeyRight++;
                current++;
            }
            max = Math.max(current, max);

        }

        return max;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "LongestContainedInterval.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
