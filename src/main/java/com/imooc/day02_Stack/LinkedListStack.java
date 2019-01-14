package com.imooc.day02_Stack;

import com.imooc.day03_LinkedList.LinkedList;

/**
 * 使用链表实现栈
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<E>();
    }

    public int getSize() {
        return list.getSize();
    }

    public boolean isEmpty() {
        return list.getSize() == 0;
    }
    //O(1)
    public void push(E e) {
        list.addFirst(e);
    }
    //O(1)
    public E pop() {
        return list.removeFirst();
    }
    //O(1)
    public E peek() {
        return list.getFirst();
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> integerArrayStack = new LinkedListStack<Integer>();
        for (int i = 0; i < 5; i++) {
            integerArrayStack.push(i);
            System.out.println(integerArrayStack.toString());
        }
        integerArrayStack.pop();
        System.out.println(integerArrayStack.toString());
    }
}
