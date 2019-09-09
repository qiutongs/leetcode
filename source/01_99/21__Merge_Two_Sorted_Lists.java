/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Initialize a dummy head for convinience.
        // The real head will be "dummyHead.next".
        ListNode dummyHead = new ListNode(0), tail = dummyHead;

        // Choose the smaller of element of two lists as next one.
        while(l1 != null && l2 != null) {
            ListNode nextNode = null;
            if (l1.val <= l2.val) {
                nextNode = l1;
                l1 = l1.next;
            } else {
                nextNode = l2;
                l2 = l2.next;
            }

            tail.next = nextNode;
            tail = nextNode;
        }

        // Handle left over list.
        ListNode leftNode = l1 == null ? l2 : l1;

        while(leftNode != null) {
            tail.next = leftNode;
            tail = leftNode;
            leftNode = leftNode.next;
        }

        ListNode head = dummyHead.next;
        dummyHead = null;
        return head;
    }
}