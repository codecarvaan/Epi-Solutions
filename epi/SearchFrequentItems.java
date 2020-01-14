package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.*;
import java.util.function.BiPredicate;

class obj {

    public String str;
    public Integer i;

    obj(String str, Integer i) {
        this.str = str;
        this.i = i;
    }

    @Override
    public String toString() {
        return "Str->" + str + " int->" + i;
    }

}

public class SearchFrequentItems {

    @EpiTestComparator
    public static BiPredicate<List<String>, List<String>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    public static List<String> searchFrequentItems(int k, Iterable<String> stream) {

        //TODO
        return null;
    }

    @EpiTest(testDataFile = "search_frequent_items.tsv")
    public static List<String> searchFrequentItemsWrapper(int k, List<String> stream) {
        return searchFrequentItems(k, stream);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "SearchFrequentItems.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
