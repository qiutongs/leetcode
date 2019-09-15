/* 
 * Iterative solution: index K has its parent in row N - 1, and so on... until first row
 * 1. compute all parent index
 * 2. from first row compute to row N
 * 
 * Time: O(N)
 * Space: O(N)
 */
class Solution1 {
    public int kthGrammar(int N, int K) {
        int[] parentsIndex = new int[N];
        int result = 0;
        
        for (int i = N - 1; i >= 0; i--) {
            parentsIndex[i] = K - 1;
            K = (K + 1) / 2;
        }
        
        for (int i = 1; i < N; i++) {
            // right child
            if (parentsIndex[i] == parentsIndex[i - 1] * 2 + 1) {
                result ^= 1;
            }
        }
        
        return result;
    }
}

/* 
 * Recursive solution.
 * Time O(N)
 * Space O(N)
 */
class Solution2 {
    public int kthGrammar(int N, int K) {
        if (N == 1) {
            return 0;
        }
        
        int parentIndex = (K + 1) / 2;
        int parentVal = kthGrammar(N - 1, parentIndex);
        // right child
        if (K == parentIndex * 2) {
            return parentVal ^ 1;
        } else { // left child
            return parentVal;
        }
    }
}