class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        ArrayList<Integer> ret = new ArrayList<>();
        
        int p1 = 0, p2 = 0;
        
        while(p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                ret.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        
        int[] unboxedRet = new int[ret.size()];
        for (int i = 0; i < ret.size(); i++) {
            unboxedRet[i] = ret.get(i);
        }
        return unboxedRet;
    }
}