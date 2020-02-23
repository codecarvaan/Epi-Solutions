package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
      Double maxProfit = 0d;
      Double minSofar = Double.MAX_VALUE;
      for (Double num : prices) {
          if (num > minSofar) {
              maxProfit = Math.max(maxProfit, num - minSofar);
          } else {
              minSofar = Math.min(minSofar, num);
          }
      }
      return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
