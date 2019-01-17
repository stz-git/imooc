package com.imooc.day03_LinkedList.leetcode;

/**
 * 链表翻转的迭代方式与递归方式
 */
public class ListNodeReverse {

    //迭代是从链表头节点开始执行
    public static ListNode reverse(ListNode head) {
        ListNode dummyHead = new ListNode(-1);

        while(head != null) {
            //save head's nextNode
            ListNode nextNode = head.next;

            //exchange
            head.next = dummyHead.next;
            dummyHead.next = head;

            head = nextNode;
        }
        return dummyHead.next;
    }

    //递归使链表从尾节点开始执行
    public static ListNode reverse2(ListNode head) {

        if(head.next == null || head == null){
            return head;
        }
        ListNode res = reverse2(head.next);
        //从链表尾节点开始改变指针指向
        head.next.next = head;
        head.next = null;

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        ListNode head = new ListNode(arr);
        System.out.println(head.toString());

        head = reverse2(head);
        System.out.println(head);
    }
}
