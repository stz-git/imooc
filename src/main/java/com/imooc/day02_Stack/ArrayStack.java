package com.imooc.day02_Stack;

import com.imooc.day01_Array.HomeWorkArray;

/**
 * 基于动态数组实现栈
 * 栈是一种特殊的数组
 *
 * 用户只能看见栈顶的元素是什么
 * @param <E>
 */
public class ArrayStack<E> implements Stack<E> {

    private HomeWorkArray<E> extArray;

    public ArrayStack(int capacity) {
        extArray = new HomeWorkArray<E>(capacity);
    }

    public ArrayStack() {
        extArray = new HomeWorkArray<E>();
    }

    //O(1)
    public void push(E e) {
        extArray.addLast(e);
    }

    //O(n)
    public E pop() {
        return extArray.removeLast();
    }

    public E peek() {
        return extArray.getLast();
    }

    public int getSize() {
        return extArray.getSize();
    }

    public boolean isEmpty() {
        return extArray.isEmpty();
    }

    public int getCapacity() {
        return extArray.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < getSize(); i++) {
            res.append(extArray.get(i).toString());
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> integerArrayStack = new ArrayStack<Integer>();
        for (int i = 0; i < 5; i++) {
            integerArrayStack.push(i);
            System.out.println(integerArrayStack.toString());
        }
        integerArrayStack.pop();
        System.out.println(integerArrayStack.toString());
    }
}
