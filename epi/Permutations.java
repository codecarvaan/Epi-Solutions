package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class Permutations {
    @EpiTestComparator
    public static BiPredicate<List<List<Integer>>, List<List<Integer>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                for (List<Integer> l : expected) {
                    Collections.sort(l);
                }
                expected.sort(new LexicographicalListComparator<>());
                for (List<Integer> l : result) {
                    Collections.sort(l);
                }
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
            };

    @EpiTest(testDataFile = "permutations.tsv")

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> partial = new ArrayList();
        recursion(result, 0, A);
        return result;
    }

    public static void recursion(List<List<Integer>> result, int itr, List a) {
        if (itr == a.size() - 1) {
            result.add(a);
            return;
        }
        for (int i = itr; i < a.size(); i++) {
            swap(i, itr, a);
            recursion(result, itr + 1, a);
            swap(i, itr, a);
        }
    }

    public static void swap(int a, int b, List arr) {
        Integer temp = (Integer) arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, temp);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Permutations.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
