package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringDecompositionsIntoDictionaryWords {
    @EpiTest(testDataFile = "string_decompositions_into_dictionary_words.tsv")

    public static List<Integer> findAllSubstrings(String s, List<String> words) {

        HashMap<String, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        int unitSize = words.get(0).length();
        for (int i = 0; i + unitSize * words.size() <= s.length(); i++) {
            if (match(s, i, map, unitSize, words.size())) {
                result.add(i);
            }
        }

        return result;
    }

    public static boolean match(String s, Integer start, Map<String, Integer> map, Integer unitSize, Integer wordSize) {

        HashMap<String, Integer> temp = new HashMap<>();
        for (int i = 0; i < wordSize; ++i) {
            String key = s.substring(start + unitSize * i, start + unitSize * (i + 1));
            Integer keyfreq = map.get(key);
            if (keyfreq == null) {
                return false;
            } 
            temp.put(key, temp.getOrDefault(key, 0) + 1);
            if (temp.get(key) > keyfreq) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(GenericTest
                .runFromAnnotations(
                        args, "StringDecompositionsIntoDictionaryWords.java",
                        new Object() {
                        }.getClass().getEnclosingClass())
                .ordinal());
    }
}
