/*
DP: leverage the math fact: An = min(Ai * 2, Aj * 3, Ak * 5)

- conceptually, there are 3 list
- in fact, there is only 1 list

https://www.geeksforgeeks.org/ugly-numbers/
*/
class Solution {
    public int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        
        int pTwo = 0, pThree = 0, pFive = 0;
        
        for (int i = 1; i < n; i++) {
            int nextTwo = uglyNums[pTwo] * 2;
            int nextThree = uglyNums[pThree] * 3;
            int nextFive = uglyNums[pFive] * 5;
            
            uglyNums[i] = Math.min(nextTwo, Math.min(nextThree, nextFive));
            
            if (nextTwo == uglyNums[i]) {
                pTwo++;
            }
            
            if (nextThree == uglyNums[i]) {
                pThree++;
            }
            
            if (nextFive == uglyNums[i]) {
                pFive++;
            }
        }
        
        return uglyNums[n-1];
    }
}