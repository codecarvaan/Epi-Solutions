package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;



public class Parity {
  @EpiTest(testDataFile = "parity.tsv")
  public static short parity(long x) {
    int lookup[]=new int[65535];
    for(int i=0;i<lookup.length;i++){
      int count=0;
      int a=i;
      while(a!=0){
        count++;
        a=a&(a-1);
      }
      if(count%2==0){
        lookup[i]=0;
      }else{
        lookup[i]=1;
      }
    }

    return (short) (lookup[(int) (x>>(16*3))]^lookup[(int) (x>>(16*2)&65535)]^lookup[(int) ((x>>16)&65535)]^lookup[(int) (x&65535)]);
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "Parity.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
