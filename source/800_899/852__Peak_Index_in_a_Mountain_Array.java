class Solution1 {
    public int peakIndexInMountainArray(int[] A) {
        int l = 0, r = A.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (mid == A.length - 1 || A[mid] > A[mid + 1]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l;
    }
}

class Solution2 {
    public int peakIndexInMountainArray(int[] A) {
        int l = 0, r = A.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (mid == 0 || A[mid] > A[mid - 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return r;
    }
}