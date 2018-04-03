class Solution {
    public void rotate(int[][] matrix) {
        int maxIterations = matrix.length/2;
        int i = 0;
        while (i < maxIterations){
            rotateIteration(matrix, i);
            i++;
        }
    }
    
    //depth starts with 0
    private void rotateIteration(int[][] matrix, int depth){
        int n = matrix.length;
        int firstIndex = depth;
        int lastIndex = n-depth-1;
        
        for (int i = 0; i < n-2*(depth)-1; i++){
            int tmp = matrix[firstIndex][firstIndex+i];
            //up-left = bottom-left
            matrix[firstIndex][firstIndex+i] = matrix[lastIndex-i][firstIndex];
            //bottom-left = bottom-right
            matrix[lastIndex-i][firstIndex] = matrix[lastIndex][lastIndex-i];
            //bottom-right = up-right
            matrix[lastIndex][lastIndex-i] = matrix[firstIndex+i][lastIndex];
            //up-right = bottom-left
            matrix[firstIndex+i][lastIndex] = tmp;
        }
    }
}
