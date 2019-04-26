/*
Double reverse...

*/
class Solution1 {
    //A1A2...An-kAn-k+1...An -> An-k+1..AnA1A2...An-k
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) return;

        k = k % nums.length;

        //An...An-k+1An-k...A1
        reverse(nums,0,nums.length-1);
        //An-k+1...AnAn-k...A1
        reverse(nums,0,k-1);
        //An-k+1...AnA1...An-k
        reverse(nums,k,nums.length-1);
    }

    private void reverse(int[] nums, int start, int end){
        while(start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}

/*
Cyclic replacement:
- there are one or more chain with same length
- the number of chains is the greatest common dividor of the array's size and k
For example:
n = 7, k = 3 -> 1 chain
n = 6, k = 4 -> 2 chains
n = 10, k = 5 -> 5 chains

*/
class Solution2 {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int endPos = nums.length - 1;

        k = k % n;

        while (n > 0) {
            n -= shiftRight(nums, k, endPos);
            endPos--;
        }
    }

    private int shiftRight(int[] nums, int k, int endPos) {
        // Save the rightmost value, used in the end of shift.
        int tmp = nums[endPos];

        // Track how many shift has been done.
        int count = 0;

        int curPos = endPos;

        do {
            // Compute the position for its previous index.
            int prePos = (curPos + nums.length - k) % nums.length;
            count++;

            // If this is the end of chain.
            if (prePos == endPos) {
                nums[curPos] = tmp;
                break;
            } else { // Set current postition to the value of previous postion.
                nums[curPos] = nums[prePos];
                curPos = prePos;
            }

        } while(true);

        return count;
    }
}
