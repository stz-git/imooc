package com.imooc.day03_LinkedList.leetcode;

public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {

        //使用虚拟头节点，避免区分头结点和其他节点
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while(cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }
}
