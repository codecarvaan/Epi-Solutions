package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class IntervalAdd {
    @EpiTest(testDataFile = "interval_add.tsv")

    public static List<Interval> addInterval(List<Interval> disjointIntervals,
                                             Interval newInterval) {
        // TODO - you fill in here.
        int itr = 0;
        List<Interval> result = new ArrayList<>();
        for (Interval i : disjointIntervals) {
            if (i.right < newInterval.left) {
                itr++;
                result.add(i);
            }
        }
        while (itr < disjointIntervals.size() && newInterval.right >= disjointIntervals.get(itr).left) {
            newInterval = new Interval(Math.min(disjointIntervals.get(itr).left, newInterval.left), Math.max(disjointIntervals.get(itr).right, newInterval.right));

            itr++;
        }
        result.add(newInterval);

        result.addAll(disjointIntervals.subList(itr, disjointIntervals.size()));
        return null;
    }

    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Interval interval = (Interval) o;

            if (left != interval.left) {
                return false;
            }
            return right == interval.right;
        }

        @Override
        public String toString() {
            return "[" + left + ", " + right + "]";
        }

        static class Time {
            int start;
            boolean status;

            Time(int Start, boolean status) {
                this.start = start;
                this.status = status;
            }
        }
    }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntervalAdd.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
