/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }

 Ideas:
 - remove duplicated one by one
 - goto next non-duplciated one: delete multiple elements at a time
 */
class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curNode = head.next, preNode = head;

        while(curNode != null) {
            if (curNode.val == preNode.val) {
                preNode.next = curNode.next;
            } else {
                preNode = curNode;
            }

            curNode = curNode.next;
        }

        return head;
    }
}

class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tail = head, curNode = head;

        while(curNode != null) {
            int curVal = curNode.val;
            do {
                curNode = curNode.next;
            } while (curNode != null && curNode.val == curVal);

            tail.next = curNode;
            tail = curNode;
        }

        return head;
    }
}

class Solution3 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode curNode = head;

        while(curNode != null) {
            ListNode nextDiffNode = curNode.next;

            while(nextDiffNode != null && nextDiffNode.val == curNode.val) {
                nextDiffNode = nextDiffNode.next;
            }

            curNode.next = nextDiffNode;
            curNode = nextDiffNode;
        }

        return head;
    }
}
