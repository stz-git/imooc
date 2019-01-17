package com.imooc.day03_LinkedList.leetcode;

/**
 * 删除链表中等于给定值 val 的所有节点
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr){
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            res.append(cur.val+"->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}

public class Solution{

        /**
         * 链表中删除元素是找到该元素的上一个节点，将上一个节点的next改为当前元素的下一个节点
         * 因为head没有上一个节点，所以要和其他节点分开处理
         * @param head
         * @param val
         * @return
         */
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if(head == null){//两种情况：1.第一个上来的head就是null，遍历到最后所有节点的value都和val相等
            return null;
        }

        ListNode cur = head;
        while(cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}
