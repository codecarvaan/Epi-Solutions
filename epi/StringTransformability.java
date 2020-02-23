package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public class StringTransformability {

    @EpiTest(testDataFile = "string_transformability.tsv")

    // Uses BFS to find the least steps of transformation.
    public static int transformString(Set<String> D, String s, String t) {

        Deque<StringWithDistance> list = new LinkedList<>();
        list.add(new StringWithDistance(s, 0));
        D.remove(s);

        while (!list.isEmpty()) {
            StringWithDistance stringWithDistance = list.pollFirst();
            if (stringWithDistance.str.equals(t)) {
                return stringWithDistance.distance;
            }
            for (int i = 0; i < stringWithDistance.str.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    StringBuilder temp = new StringBuilder(stringWithDistance.str);
                    temp.setCharAt(i, (char) ('a' + j));

                    if (D.remove(temp.toString())) {

                        list.addLast(new StringWithDistance(temp.toString(), stringWithDistance.distance + 1));
                    }
                }
            }

        }

        return -1;
    }

    static class StringWithDistance {
        String str;
        int distance;

        StringWithDistance(String str, int distance) {
            this.str = str;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "StringTransformability.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
