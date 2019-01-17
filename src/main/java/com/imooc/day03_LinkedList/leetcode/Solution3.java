package com.imooc.day03_LinkedList.leetcode;

public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        //最小问题
        if(head == null)
            return null;

        //后面那一串给我的正确链表
        ListNode res = removeElements(head.next, val);

        //我要做的就是决定是否把我拼上正确链表返回给上一层
        if(head.val == val){
           return res;
        }else{
            head.next = res;
            return head;
        }
    }
}
