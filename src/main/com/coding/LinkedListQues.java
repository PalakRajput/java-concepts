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
        while(temp != null || i < gcd.size()){
            if(turn == 0 && temp != null){
                tempRes.next = new ListNode(temp.val);
                temp = temp.next;
                tempRes = tempRes.next;
                turn = 1;
            } else if(turn == 1 && i < gcd.size()) {
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
}
