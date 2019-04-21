/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 *
 * Basic idea:
 * 1. if they meet:
 * S1 + n * circle = 2 * S1
 * 2. S1 = line + arc
 * line + arc = n * circle
 * line = n * circle - arc 
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        do {
            slow = slow.next;
            fast = fast.next == null? fast.next : fast.next.next;

            if (fast == slow && fast != null) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        } while(fast != null);

        return null;
    }
}
