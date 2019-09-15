/*
 * Maintain a min heap with size k
 * add takes O(logks)
 */
class KthLargest
{

    private PriorityQueue<Integer> pq;
    private int k;

    public KthLargest(int k, int[] nums)
    {
        this.pq = new PriorityQueue<Integer>(k);
        this.k = k;

        for (int i = 0; i < k && i < nums.length; i++)
        {
            pq.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++)
        {
            add(nums[i]);
        }
    }

    public int add(int val)
    {

        if (val > pq.peek())
        {
            pq.poll();
            pq.offer(val);
        }

        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */