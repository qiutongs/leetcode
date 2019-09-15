/* 
 * suboptimal solution: O(klogk)
 * similar to problem: kth in sorted matrix
 *
 * Warn: not pass all tests 
 */
class Solution {
    public int findKthNumber(int m, int n, int k) {
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }
        
        pq.offer(new Node(0, 0));
        visited[0][0] = true;
        
        while(k > 1) {
            Node minNode = pq.poll();
            
            if (minNode.i + 1 < m && visited[minNode.i + 1][minNode.j] == false) {
                pq.offer(new Node(minNode.i + 1, minNode.j));
                visited[minNode.i + 1][minNode.j] = true;
            }
            
            if (minNode.j + 1 < n && visited[minNode.i][minNode.j + 1] == false) {
                pq.offer(new Node(minNode.i, minNode.j + 1));
                visited[minNode.i][minNode.j + 1] = true;
            }
            
            k--;
        }
        
        return pq.peek().val;
    }
    
    private class Node implements Comparable<Node> {
        int i;
        int j;
        int val;
        
        Node(int i, int j) {
            this.i = i;
            this.j = j;
            this.val = (i + 1) * (j + 1);
        }
        
        public int compareTo(Node node) {
            return this.val - node.val;
        }
    }
}