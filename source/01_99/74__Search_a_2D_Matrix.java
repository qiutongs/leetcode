class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int l = 0, r = m * n - 1;
        
        // x = i * n + j
        // 0 <= i < m
        // 0 <= j < n
        while(l <= r) {
            int mid = (l + r) / 2;
            int midi = mid / n, midj = mid % n;
            
            if (target < matrix[midi][midj]) {
                r = mid - 1;
            } else if (target > matrix[midi][midj]) {
                l = mid + 1;
            } else {
                return true;
            }
        }
        
        return false;
    }
}