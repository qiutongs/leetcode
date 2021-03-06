
/*
sum up all the "uprise" gain
*/
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int result = 0;
        int curPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > curPrice) {
                result += prices[i] - curPrice;
            }
            curPrice = prices[i];
        }

        return result;
    }
}
