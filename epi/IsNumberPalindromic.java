package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsNumberPalindromic {
    @EpiTest(testDataFile = "is_number_palindromic.tsv")
    public static boolean isPalindromeNumber(int x) {
        int temp = x;
        int rev = 0;
        if (x < 0) {
            return false;
        }
        while (temp != 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }
        return rev == x ? true : false;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsNumberPalindromic.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
