/*
 * Similar to problem: find kth in sorted matrix
 */
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 || nums2.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> result = new LinkedList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[nums1.length][nums2.length];
        
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0;j < nums2.length; j++) {
                visited[i][j] = false;
            }
        }
        
        pq.offer(createPair(0, 0, nums1, nums2));
        visited[0][0] = true;
        
        while(k > 0) {
            
            if (pq.size() == 0) {
                break;
            }
            
            Pair minPair = pq.poll();
            
            result.add(Arrays.asList(nums1[minPair.i], 
                                     nums2[minPair.j]));
            
            if (minPair.i + 1 < nums1.length && visited[minPair.i + 1][minPair.j] == false) {
                pq.offer(createPair(minPair.i + 1, minPair.j, nums1, nums2));
                visited[minPair.i + 1][minPair.j] = true;
            }
            
            if (minPair.j + 1 < nums2.length && visited[minPair.i][minPair.j + 1] == false) {
                pq.offer(createPair(minPair.i, minPair.j + 1, nums1, nums2));
                visited[minPair.i][minPair.j + 1] = true;
            }
            
            k--;
        }
        
        return result;
    }
    
    private Pair createPair(int i, int j, int[] nums1, int[] nums2) {
        return new Pair(i, j, nums1[i] + nums2[j]);
    }
    
    private class Pair implements Comparable<Pair> {
        int i;
        int j;
        int val;
        
        Pair(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
        
        public int compareTo(Pair other) {
            return this.val - other.val;
        }
    }
}