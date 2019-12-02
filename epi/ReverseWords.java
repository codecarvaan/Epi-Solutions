package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;

import java.util.Collections;

public class ReverseWords {

    public static void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void reverseWordUtil(char[] array, int start, int end) {
        while (start < end) {
            swap(array, start++, end--);
        }
    }

    public static void reverseWords(char[] input) {

        reverseWordUtil(input, 0, input.length - 1);
        int start = 0;
        int end;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == ' ') {
                end = i - 1;
                reverseWordUtil(input, start, end);
                start = i + 1;
            } else if (i == input.length - 1) {
                reverseWordUtil(input, start, i);
            }
        }
        return;
    }

    @EpiTest(testDataFile = "reverse_words.tsv")
    public static String reverseWordsWrapper(TimedExecutor executor, String s)
            throws Exception {
        char[] sCopy = s.toCharArray();

        executor.run(() -> reverseWords(sCopy));

        return String.valueOf(sCopy);
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "ReverseWords.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
