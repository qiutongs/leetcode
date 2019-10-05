class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Collections.emptyList();
        }
        
        List<List<Integer>> ret = new LinkedList<>();
        
        Arrays.sort(arr);
        
        int minAbs = Integer.MAX_VALUE;
        
        for (int i = 1; i < arr.length; i++) {
            minAbs = Math.min(minAbs, arr[i] - arr[i - 1]);
        }
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minAbs) {
                List<Integer> pair = new ArrayList<>(2);
                pair.add(arr[i - 1]);
                pair.add(arr[i]);
                ret.add(pair);
            }
        }
        
        return ret;
    }
}