package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ABSqrt2 {
  @EpiTest(testDataFile = "a_b_sqrt2.tsv")

  public static List<Double> generateFirstKABSqrt2(int k) {
    List<Double> result = new ArrayList();
    TreeSet<Number> set = new TreeSet<>((a, b) -> Double.compare(a.val, b.val));
    set.add(new Number(0, 0));
    while (result.size() < k) {
      Number low = set.first();
      result.add(low.val);
      set.add(new Number(low.a + 1, low.b));
      set.add(new Number(low.a, low.b + 1));
      set.remove(low);
    }
    return result;
  }

  public static class Number {
    int a;
    int b;
    double val;

    public Number(int a, int b) {
      this.a = a;
      this.b = b;
      val = a + b * Math.sqrt(2);

    }
  }


  public static void main(String[] args) {
    System.exit(
            GenericTest
                    .runFromAnnotations(args, "ABSqrt2.java",
                            new Object() {
                            }.getClass().getEnclosingClass())
                    .ordinal());
  }
}
