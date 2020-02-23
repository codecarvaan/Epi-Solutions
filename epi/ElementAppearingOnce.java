package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class ElementAppearingOnce {
  @EpiTest(testDataFile = "element_appearing_once.tsv")

  public static int findElementAppearsOnce(List<Integer> A) {

    int arr[] = new int[32];

    for (Integer a : A) {
      int index = 0;
      while (a != 0 && index < 32) {
        arr[index] = arr[index] + (a & 1);
        a = a >> 1;
        index++;
      }
    }
    int num = 0;
    for (int i = 31; i >= 0; i--) {
      num = num << 1 | arr[i] % 3;
    }

    return num;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ElementAppearingOnce.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
