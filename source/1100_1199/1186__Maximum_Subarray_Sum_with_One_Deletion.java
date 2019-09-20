/* DP 
*/
class Solution {
    public int maximumSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        int maxNoDel = arr[0], maxOneDel = 0;
        int ret = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            // already deleted one or delete current
            maxOneDel = Math.max(maxOneDel + arr[i], maxNoDel);
            // no delete at all
            maxNoDel = maxNoDel < 0 ? arr[i] : maxNoDel + arr[i];
            
            ret = Math.max(ret, maxOneDel);
            ret = Math.max(ret, maxNoDel);
        }
        
        return ret;
    }
}