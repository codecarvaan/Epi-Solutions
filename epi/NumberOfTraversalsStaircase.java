package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class NumberOfTraversalsStaircase {
  @EpiTest(testDataFile = "number_of_traversals_staircase.tsv")

  public static int numberOfWaysToTop(int top, int maximumStep) {

      int arr[] = new int[top + 1];
      arr[0] = 1;
//    for (int i = 1; i <= top; i++) {
//      for (int j = 1; j <= maximumStep; j++) {
//        if (i >= j) {
//          arr[i] = arr[i] + arr[i - j];
//        }
//      }
//    }
//    return arr[top];
      return computeNumberOfWaysToH(top, arr, maximumStep);
  }

    public static int computeNumberOfWaysToH(int n, int numberOfWaysToH[], int maximumStep) {
        if (n <= 1) {
            return 1;
        }
        if (numberOfWaysToH[n] == 0) {
            for (int i = 1; i <= maximumStep && n - i >= 0; ++i) {
                numberOfWaysToH[n] += computeNumberOfWaysToH(n - i, numberOfWaysToH, maximumStep);
            }
        }
        return numberOfWaysToH[n];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "NumberOfTraversalsStaircase.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
