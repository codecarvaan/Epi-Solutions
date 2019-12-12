package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;
import epi.test_framework.LexicographicalListComparator;

import java.util.*;
import java.util.function.BiPredicate;

public class Anagrams {

      public static String sort(String str) {
        char arr[] = str.toCharArray();
        Arrays.sort(arr);
        return Arrays.toString(arr);

    }

    @EpiTest(testDataFile = "anagrams.tsv")
    public static List<List<String>> findAnagrams(List<String> dictionary) {

        Map<String, List> map = new HashMap();
        for (String str : dictionary) {
            String sorted = sort(str);

            if (map.containsKey(sorted)) {
                map.get(sorted).add(str);
            } else {
                List ls = new ArrayList();
                ls.add(str);
                map.put(sorted, ls);
            }
        }
        Iterator<String> itr=map.keySet().iterator();
        List<List<String>> result=new ArrayList<>();
        while(itr.hasNext()){
          String key=itr.next();
          if(map.get(key).size()>1){
          result.add(map.get(key));}
        }

        return result;
    }

    @EpiTestComparator
    public static BiPredicate<List<List<String>>, List<List<String>>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                for (List<String> l : expected) {
                    Collections.sort(l);
                }
                expected.sort(new LexicographicalListComparator<>());
                for (List<String> l : result) {
                    Collections.sort(l);
                }
                result.sort(new LexicographicalListComparator<>());
                return expected.equals(result);
            };

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "Anagrams.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
