package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
  @EpiTest(testDataFile = "swap_bits.tsv")
  public static long swapBits(long x, int i, int j) {

      long a=1L<<i;    //10000
      long b=1L<<j;       // 100
      if((((x & a) == a) && ((x & b) == b)) || (((x & a) == 0) && (x & b) == 0))
          return x;

      x=(x^a)^b;

    return x;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SwapBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
