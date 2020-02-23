package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
  @EpiTest(testDataFile = "prime_sieve.tsv")
  // Given n, return all primes up to and including n.
  public static List<Integer> generatePrimes(int n) {

      List<Boolean> isPrime = new ArrayList<>(Collections.nCopies(n + 1, true));
      List<Integer> ans = new ArrayList<>();
      isPrime.set(0, false);
      isPrime.set(1, false);
      for (int i = 2; i <= n; i++) {
          if (isPrime.get(i)) {
              ans.add(i);
              for (int j = i; j <= n; j += i) {
                  isPrime.set(j, false);
              }

          }

      }
      return ans;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "PrimeSieve.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
