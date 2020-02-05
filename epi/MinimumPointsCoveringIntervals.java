package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class MinimumPointsCoveringIntervals {
    @EpiTest(testDataFile = "minimum_points_covering_intervals.tsv")

    public static Integer findMinimumVisits(List<Interval> intervals) {

        intervals.sort((i1, i2) -> Integer.compare(i1.right, i2.right));
        if (intervals.size() == 0) {
            return 0;
        }
        int left = intervals.get(0).right;
        List<Integer> list = new ArrayList<>();
        list.add(left);

        for (int i = 1; i < intervals.size(); i++) {

            if (intervals.get(i).left > left) {
                list.add(intervals.get(i).right);
                left = intervals.get(i).right;
            }
        }

        return list.size();
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "MinimumPointsCoveringIntervals.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }
}
