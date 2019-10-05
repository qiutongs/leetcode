class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int[] ret = new int[intervals.length];
        
        Pair[] pairs = new Pair[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            pairs[i] = new Pair(intervals[i][0], intervals[i][1], i);
        }
        
        // sort by start point
        Arrays.sort(pairs, (p1, p2) -> p1.start - p2.start);
        
        for (int i = 0; i < pairs.length; i++) {
            Pair right = binarySearch(pairs, pairs[i].end, i + 1);
            // target is current end point
            ret[pairs[i].index] = right == null ? -1 : right.index;
        }
        
        return ret;
    }
    
    private Pair binarySearch(Pair[] pairs, int target, int l) {
        int r = pairs.length - 1;
        
        while(l <= r) {
            int mid = (l + r) / 2;
            
            if (target <= pairs[mid].start) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l == pairs.length ? null : pairs[l];
    }
    
    private class Pair {
        int start;
        int end;
        int index;
        
        Pair(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }
}