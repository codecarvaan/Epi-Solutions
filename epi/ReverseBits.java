package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
  @EpiTest(testDataFile = "reverse_bits.tsv")
  public static long reverseBits(long x) {

    long temp=0;

    temp=(((x>>16*3)^65536)<<16)+(((x>>16*2)^65536)<<16)+(((x>>16)^65536)<<16)+(x^65536);  //extract most significant word of 16 bit

    return temp;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ReverseBits.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
