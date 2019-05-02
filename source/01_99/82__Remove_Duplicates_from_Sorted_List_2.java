/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curNode = head;

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;

        while(curNode != null) {
            ListNode nextDiffNode = curNode.next;

            while(nextDiffNode != null && nextDiffNode.val == curNode.val) {
                nextDiffNode = nextDiffNode.next;
            }

            // If next diff is adjacent, then curNode is a valid one.
            if (curNode.next == nextDiffNode) {
                tail.next = curNode;
                tail = curNode;
            }

            curNode = nextDiffNode;
        }

        tail.next = null;

        return dummyHead.next;
    }
}
