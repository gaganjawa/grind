package org.dsa.linkedlist;

import java.util.LinkedList;

public class SwapPairs {

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2, new ListNode(3));

        System.out.println(swapPairs(head));

    }

    public static ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode first = head;
        ListNode second = head.next;

        while(first != null && second != null) {
            ListNode third = second.next;

            second.next = first;
            first.next = third;
            if(prev != null) {
                prev.next = second;
            } else {
                head = second;
            }
            prev = first;
            first = third;
            if(third != null) {
                second = third.next;
            } else {
                second = null;
            }
        }
        return head;
    }
}
