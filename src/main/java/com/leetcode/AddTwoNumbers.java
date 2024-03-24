package com.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class AddTwoNumbers {

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
//        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode sum = addTwoNumbers(l1, l2);
        System.out.println(sum.val);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        AtomicInteger sizeCounter = new AtomicInteger(0);
        AtomicInteger reminder = new AtomicInteger(0);

        ListNode newListNode;
        try {
            newListNode = getNode(l1, l2, reminder, sizeCounter);
        } catch (IllegalArgumentException e) {
            return null;
        }
        if (sizeCounter.get() < 1 || sizeCounter.get() > 100) {
            return null;
        } else {
            return newListNode;
        }
    }

    private static ListNode getNode(ListNode ln1, ListNode ln2, AtomicInteger remainder, AtomicInteger sizeCounter) {
        int rem = remainder.get();
        if (ln1 == null && ln2 == null) {
            return rem == 0 ? null : new ListNode(rem);
        }
        sizeCounter.incrementAndGet();
        int sum = getValue(ln1) + getValue(ln2) + rem;
        int nextValue = sum % 10;
        remainder.set(sum / 10);
        return new ListNode(nextValue, getNode(getNext(ln1), getNext(ln2), remainder, sizeCounter));
    }

    private static ListNode getNext(ListNode listNode) {
        return listNode != null ? listNode.next : null;
    }

    private static int getValue(ListNode listNode) {

        if (listNode == null) {
            return 0;
        } else {
            int val = listNode.val;
            if (val > 9 || val < 0) {
                throw new IllegalArgumentException();
            }
            return val;
        }
    }

    static class ListNode {
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
}
