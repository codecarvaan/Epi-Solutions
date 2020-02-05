package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;

public class PickingUpCoins {
    @EpiTest(testDataFile = "picking_up_coins.tsv")

    public static int pickUpCoins(List<Integer> coins) {

        int cache[][] = new int[coins.size()][coins.size()];
        return util(coins, 0, coins.size() - 1, cache);
    }

    public static int util(List<Integer> coins, int a, int b, int cache[][]) {

        if (a > b) {
            return 0;
        }
        if (cache[a][b] == 0) {
            int selecta = coins.get(a) + Math.min(util(coins, a + 2, b, cache), util(coins, a + 1, b - 1, cache));
            int selectb = coins.get(b) + Math.min(util(coins, a + 1, b - 1, cache), util(coins, a, b - 2, cache));
            cache[a][b] = Math.max(selecta, selectb);
        }
        return cache[a][b];
    }

    public static void main(String[] args) {
        System.exit(
                GenericTest
                        .runFromAnnotations(args, "PickingUpCoins.java",
                                new Object() {
                                }.getClass().getEnclosingClass())
                        .ordinal());
    }
}
