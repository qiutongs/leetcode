class Solution {
    public int change(int amount, int[] coins) {
        // Index is the amount; value is the number of combinations.
        // Initialize to 0 to indicate no combinations.
        int[] combinations = new int[5001];
        
        // Pre-compute coins: sort and remove duplicates.
        List<Integer> coinsList = new ArrayList<>(coins.length);
        for(int coin : coins) {
            coinsList.add(coin);
        }
        coinsList = coinsList.stream().sorted().distinct().collect(Collectors.toList());
        
        // Base case: 1 combination of amount 0.
        combinations[0] = 1;
        
        for (int i = 0; i <= amount; i++) {
            // If there is a combination 
            if (combinations[i] > 0) {
                for (int coin : coins) {
                    if (i <= amount) {
                        combinations[i + coin] += combinations[i];  
                    }
                }
            }
        }
        
        return combinations[amount];
    }
}


class Solution {
    public int change(int amount, int[] coins) {
        int[] result = { 0 };
        
        // Pre-compute coins: sort and remove duplicates.
        List<Integer> coinsList = new ArrayList<>(coins.length);
        for(int coin : coins) {
            coinsList.add(coin);
        }
        coinsList = coinsList.stream().sorted().distinct().collect(Collectors.toList());
        
        backtrack(result, coinsList, 0, amount);
        
        return result[0];
    }
    
    private void backtrack(int[] result, List<Integer> coinsList, int curCoinIndex, int amountLeft) {
        if (amountLeft == 0) {
            result[0]++;
            return;
        }

        if (curCoinIndex == coinsList.size()) {
            return;
        }
        
        do {
            backtrack(result, coinsList, curCoinIndex + 1, amountLeft);
            amountLeft -= coinsList.get(curCoinIndex);
        } while(amountLeft >= 0);
    }
}