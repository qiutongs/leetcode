/* 
  Direct k-way merge
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution1
{
    public ListNode mergeKLists(ListNode[] lists)
    {
        ListNode[] validLists = validate(lists);

        if (validLists == null)
        {
            return null;
        }

        ListNode head = null, curNode = null;

        // Build a min heap with head node in each array.
        Heap heap = new Heap(validLists);

        // While heap is not empty.
        while(heap.size() > 0)
        {
            // Replace the min with its next node if possible;
            // Otherwise, it will just delete the min.
            ListNode minNode = heap.updateMinToNext();

            // Initialize head.
            if (head == null)
            {
                head = minNode;
                curNode = minNode;
            }
            // Build new order.
            else
            {
                curNode.next = minNode;
                curNode = curNode.next;
            }
        }

        return head;
    }

    private ListNode[] validate(ListNode[] lists)
    {
        if (lists == null || lists.length == 0)
        {
            return null;
        }

        ListNode[] validLists = new ListNode[lists.length];

        int i = 0;
        for (ListNode node : lists)
        {
            if (node != null)
            {
                validLists[i++] = node;
            }
        }

        if (i == 0)
        {
            return null;
        }

        return Arrays.copyOf(validLists, i);
    }

    private class Heap
    {
        private ListNode[] elements;
        private int size;

        Heap(ListNode[] nodeList)
        {
            this.elements = Arrays.copyOf(nodeList, nodeList.length);
            this.size = nodeList.length;

            for (int i = getLastInternalIndex() ; i >= 0; i--)
            {
                siftDown(i);
            }
        }

        int size()
        {
            return size;
        }

        ListNode updateMinToNext()
        {
            ListNode result = elements[0];

            if (elements[0].next == null)
            {
                elements[0] = elements[size - 1];
                size--;
            }
            else
            {
                elements[0] = elements[0].next;
            }

            siftDown(0);

            return result;
        }

        void siftDown(int i)
        {
            ListNode curNode = elements[i];

            while(i <= getLastInternalIndex())
            {
                int minIndex = getMinNode(i);

                if (i == minIndex)
                {
                    break;
                }
                else
                {
                    swap(i, minIndex);
                    i = minIndex;
                }
            }
        }

        private int getLastInternalIndex()
        {
            return (size - 2) / 2;
        }

        private int getMinNode(int i)
        {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            int result = i;
            int min = elements[i].val;

            if (left < size && elements[left].val < min)
            {
                result = left;
                min = elements[left].val;
            }

            if (right < size && elements[right].val < min)
            {
                result = right;
                min = elements[right].val;
            }

            return result;
        }

        private void swap(int i, int j)
        {
            ListNode tmp = elements[i];
            elements[i] = elements[j];
            elements[j] = tmp;
        }
    }


}

/*
 * Iterative 2-way merge
 */
class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode[] validLists = validate(lists);

        if (validLists == null)
        {
            return null;
        }
        
        return mergeHelper(validLists, 0, validLists.length - 1);
    }
    
    private ListNode mergeHelper(ListNode[] lists, int s, int e) {
        if (s == e) {
            return lists[s];
        }
        
        int mid = (s + e) >> 1;
        
        ListNode left = mergeHelper(lists, s, mid);
        ListNode right = mergeHelper(lists, mid + 1, e);
        
        return merge(left, right);
    }
    
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode p1 = head1, p2 = head2;
        ListNode newHead = null, curNode = null;
        
        while(p1 != null && p2 != null) {
            ListNode nextNode = null;
            
            if (p1.val < p2.val) {
                nextNode = p1;
                p1 = p1.next;
            } else {
                nextNode = p2;
                p2 = p2.next;
            }
            
            if (newHead == null) {
                newHead = nextNode;
                curNode = nextNode;
            } else {
                curNode.next = nextNode;
                curNode = nextNode;
            }
        }
        
        if (p1 != null) {
            curNode.next = p1;
        } else if (p2 != null) {
            curNode.next = p2;
        }

        return newHead;
    }
        
    private ListNode[] validate(ListNode[] lists)
    {
        if (lists == null || lists.length == 0)
        {
            return null;
        }

        ListNode[] validLists = new ListNode[lists.length];

        int i = 0;
        for (ListNode node : lists)
        {
            if (node != null)
            {
                validLists[i++] = node;
            }
        }

        if (i == 0)
        {
            return null;
        }

        return Arrays.copyOf(validLists, i);
    }
}
