package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class PrimitiveMultiply {
  @EpiTest(testDataFile = "primitive_multiply.tsv")
  public static long multiply(long x, long y) {
   long sum=0;
   while(x!=0){
    if((y&1)!=0){
      sum=sum+x;
    }
    y=y>>>1;
    x=x<<1;
   }
   return sum;
  }

//  private static long add(long a, long b) {
//    long sum=0L;
//    long temp_a=a;
//    long temp_b=b;
//    long cr_in=0L;
//    long cr_out=0L;
//    long k=1;
//    while(temp_a!=0L|temp_b!=0L){
//      long ak=a&k;
//      long bk=b&k;
//      sum=ak^bk^cr_in;
//      cr_out=((ak&bk)|(ak&cr_in)|(cr_in&bk));
//      cr_in=cr_out<<1;
//      k=k<<1;
//    }
//  return sum|cr_in;
//  }

    private static long add(long a, long b) {
        while (b != 0) {
            long carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimitiveMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
