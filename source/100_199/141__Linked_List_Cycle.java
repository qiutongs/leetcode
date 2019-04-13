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
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        do {
            slow = slow.next;
            fast = fast.next == null? fast.next : fast.next.next;

            if (fast == slow && fast != null) {
                return true;
            }
        } while(fast != null);

        return false;
    }
}
