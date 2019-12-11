package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;

public class IsStringPermutableToPalindrome {
    @EpiTest(testDataFile = "is_string_permutable_to_palindrome.tsv")
    public static boolean canFormPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int odds = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                int f = map.get(s.charAt(i)) % 2 == 0 ? -1 : 1;
                odds = odds + f;
                continue;
            }
            map.put(s.charAt(i), 1);
            odds = odds + 1;
        }
        if (odds <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsStringPermutableToPalindrome.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
