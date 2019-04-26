class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int depth = n >> 1;

        // For each depth...
        for (int i = 0; i < depth; i++) {
            // For each row...
            for (int j = 0; j < n - i * 2 - 1; j++) {
                // Rotate cycle at i, i + j.
                rotateCycle(matrix, i, i + j);
            }
        }
    }

    private void rotateCycle(int[][] matrix, int i, int j) {
        int n = matrix.length;

        // (i, j) is up left.
        int tmp = matrix[i][j];
        // (n-1-j, i) is bottom left.
        matrix[i][j] = matrix[n-1-j][i];
        // (n-1-i, n-1-j) is bottom right.
        matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
        // (j, n-1-i) is up right.
        matrix[n-1-i][n-1-j] = matrix[j][n-1-i];

        matrix[j][n-1-i] = tmp;
    }
}
