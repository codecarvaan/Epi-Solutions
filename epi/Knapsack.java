package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

import java.util.Arrays;
import java.util.List;

public class Knapsack {
    @EpiTest(testDataFile = "knapsack.tsv")

    public static int optimumSubjectToCapacity(List<Item> items, int capacity) {

        int[][] V = new int[items.size()][capacity + 1];
        for (int[] v : V) {
            Arrays.fill(v, -1);
        }
        return optimum(items, items.size() - 1, capacity, V);
    }

    private static int optimum(List<Item> items, int k, int ac, int[][] V) {

        if (k < 0) {
            return 0;
        }
        if (V[k][ac] == -1) {
            int consider_i = ac < items.get(k).weight ? 0 : items.get(k).value + optimum(items, k - 1, ac - items.get(k).weight, V);
            int notcosnider = optimum(items, k - 1, ac, V);
            V[k][ac] = Math.max(consider_i, notcosnider);
        }
        return V[k][ac];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Knapsack.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }

    @EpiUserType(ctorParams = {Integer.class, Integer.class})

    public static class Item {
        public Integer weight;
        public Integer value;

        public Item(Integer weight, Integer value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
