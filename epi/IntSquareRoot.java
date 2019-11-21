package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class IntSquareRoot {
  @EpiTest(testDataFile = "int_square_root.tsv")

  public static int squareRoot(int k) {
   long left =1;
   long right=k;
   long mid=0;
   while(left<=right){
     mid=left+(right-left)/2;
     if(mid*mid<=k){
       left=mid+1;
     }
     else{
       right=mid-1;
     }
   }

    return (int) (left-1);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntSquareRoot.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
