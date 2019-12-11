package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;

public class IsAnonymousLetterConstructible {
    @EpiTest(testDataFile = "is_anonymous_letter_constructible.tsv")

    public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0;i<magazineText.length();i++){
           if(map.containsKey(magazineText.charAt(i))){
               map.put(magazineText.charAt(i),map.get(magazineText.charAt(i)) +1);
           }
           else{
               map.put(magazineText.charAt(i),1);
           }
        }

        for(int i = 0;i<letterText.length();i++){
            if(map.containsKey(letterText.charAt(i))){
                if(map.get(letterText.charAt(i))>0){
                    map.put(letterText.charAt(i),map.get(letterText.charAt(i))-1);
                    continue;
                }
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "IsAnonymousLetterConstructible.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
