package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class BuyAndSellStockTwice {
    @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
    public static double buyAndSellStockTwice(List<Double> prices) {
        // TODO - you fill in here.

        List<Double> firstBuySellProfits = new ArrayList<>();
        Double maxProfit = 0d;

        Double minSofar = Double.MAX_VALUE;
        for (Double num : prices) {
            if (num.compareTo(minSofar) == 1) {
                maxProfit = Math.max(maxProfit, num - minSofar);
            } else {
                minSofar = Math.min(minSofar, num);
            }
            firstBuySellProfits.add(maxProfit);
        }
        Double maxSofar = Double.MIN_VALUE;
        for (int i = prices.size() - 1; i >= 0; i--) {
            maxSofar = Math.max(maxSofar, prices.get(i));
            firstBuySellProfits.set(i, firstBuySellProfits.get(i) + maxSofar - prices.get(i));
            maxProfit = Math.max(maxProfit, firstBuySellProfits.get(i));
        }

        return maxProfit;

    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
