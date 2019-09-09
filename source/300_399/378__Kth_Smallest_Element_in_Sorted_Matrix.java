import javafx.util.Pair;

/*
 * Maintain a min heap. Time klogk

 * Note: this reminds me of Dijkstra algorithm for shortest path
 */
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int n = matrix.length;
        
        // Min heap: track potential small values.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // To prevent duplicate postion.
        Set<Node> visitedNodes = new HashSet<>();
        
        // Initialize the heap with the first element which is smallest.
        Node smallestNode = new Node(0, 0, matrix[0][0]);
        pq.offer(smallestNode);
        visitedNodes.add(smallestNode);
        
        // Iterate until k - 1 small elements are found.
        while(k > 1) {
            Node minNode = pq.poll();
            int i = minNode.pos.getKey();
            int j = minNode.pos.getValue();
            
            Node newNode = null;

            // Add right element to heap.
            if (j + 1 < n) {
                newNode = new Node(i, j + 1, matrix[i][j + 1]);
                
                if (!visitedNodes.contains(newNode)) {
                    pq.offer(newNode);
                    visitedNodes.add(newNode);
                }
            }
            
            // Add below element to heap.
            if (i + 1 < n) {
                newNode = new Node(i + 1, j, matrix[i + 1][j]);
                
                if (!visitedNodes.contains(newNode)) {
                    pq.offer(newNode);
                    visitedNodes.add(newNode);
                }
            }

            k--;
        }
        
        // The next smallest element is the kth.
        return pq.peek().val;
    }
    
    private class Node implements Comparable<Node> {
        Pair<Integer, Integer> pos;
        int val;
        
        Node(int i, int j, int val) {
            this.pos = new Pair<>(i, j);
            this.val = val;
        }
        
        // Comparsion is based on value.
        public int compareTo(Node other) {
            Integer thisVal = val, otherVal = other.val;
            return thisVal.compareTo(otherVal);
        }
        
        // Hash code is based on the pair of integers;
        public int hashCode() {
            return pos.hashCode();
        }
        
        // Equal is based on the pair of integers;
        public boolean equals(Object other) {
            return pos.equals(((Node)other).pos);
        }
    }
}