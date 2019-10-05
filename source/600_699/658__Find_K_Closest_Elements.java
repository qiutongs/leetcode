class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }
        
        List<Integer> ret = new ArrayList<>(k);
        
        int r = firstGE(arr, x);
        int l = r - 1;
        
        for (int i = 0; i < k; i++) {
            if (r >= arr.length) {
                ret.add(arr[l]);
                l--;
            } else if (l < 0) {
                ret.add(arr[r]);
                r++;
            } else {
                if (x - arr[l] <= arr[r] - x) {
                    ret.add(arr[l]);
                    l--;
                } else {
                    ret.add(arr[r]);
                    r++;
                }
            }
        }
        
        Collections.sort(ret);
        
        return ret;
    }
    
    private int firstGE(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (x <= arr[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l;
    }
}