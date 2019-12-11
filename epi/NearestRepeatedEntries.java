package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.HashMap;
import java.util.List;
public class NearestRepeatedEntries {
  @EpiTest(testDataFile = "nearest_repeated_entries.tsv")

  public static int findNearestRepetition(List<String> paragraph) {
    HashMap<String,Integer> map=new HashMap<>();
    int diff=paragraph.size();
    for(int i=0;i<paragraph.size();i++){
      if(map.containsKey(paragraph.get(i))){
        int newdiff=i-map.get(paragraph.get(i));
        if(diff>newdiff){
          diff=newdiff;
        }
        map.put(paragraph.get(i),i);
      }else{
        map.put(paragraph.get(i),i);
      }
    }
    return diff==paragraph.size()?-1:diff;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "NearestRepeatedEntries.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
