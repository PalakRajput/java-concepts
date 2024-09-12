package src.main.com.coding;

import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}


public class LinkedListQues {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head.next == null) {
            return head;
        }
        List<Integer> gcd = new ArrayList<>();
        ListNode temp = head;
        while (temp.next != null) {
            gcd.add(gcd(temp.val, temp.next.val));
            temp = temp.next;
        }
        temp = head;
        ListNode res = new ListNode(-1);
        ListNode tempRes = res;
        int i = 0; //
        int turn = 0; //0 -> LL, 1 -> GCD
        while (temp != null || i < gcd.size()) {
            if (turn == 0 && temp != null) {
                tempRes.next = new ListNode(temp.val);
                temp = temp.next;
                tempRes = tempRes.next;
                turn = 1;
            } else if (turn == 1 && i < gcd.size()) {
                tempRes.next = new ListNode(gcd.get(i++));
                tempRes = tempRes.next;
                turn = 0;
            }
        }
        return res.next;

    }

    private int gcd(int val1, int val2) {
        int min = Math.min(val1, val2);
        for (int i = min; i >= 1; i--) {
            if (val1 % i == 0 && val2 % i == 0) {
                return i;
            }
        }
        return 1;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        ListNode temp = head;
        List<Integer> list = new ArrayList<>();
        lengthOfLL(temp, list);
        int length = list.size();
        if (length == 0 || length == 1) {
            return head;
        }
        k = k % length;
        temp = head;
        for (int i = length - k; i < list.size(); i++) {
            temp.val = list.get(i);
            temp = temp.next;
        }
        for (int i = 0; i < length - k; i++) {
            if (temp != null) {
                temp.val = list.get(i);
                temp = temp.next;
            }
        }

        return head;

    }

    private static void lengthOfLL(ListNode head, List<Integer> list) {
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
    }

    public static ListNode partition(ListNode head, int x) {
        ListNode firstHead = new ListNode(-1);
        ListNode secondHead = new ListNode(-1);
        ListNode first = firstHead;
        ListNode second = secondHead;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val < x) {
                first.next = temp;
                first = first.next;
                temp = temp.next;
                first.next = null;
            } else {
                second.next = temp;
                second = second.next;
                temp = temp.next;
                second.next = null;
            }



        }

        first.next = secondHead.next;
        return firstHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        ListNode result = partition(head, 3);

    }
}
