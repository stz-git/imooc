package com.imooc.day02;

import com.imooc.day01.ExtArray;

/**
 * 基于动态数组实现栈
 * 栈是一种特殊的数组
 *
 * 用户只能看见栈顶的元素是什么
 * @param <E>
 */
public class ArrayStack<E> implements ExtStack<E> {

    private ExtArray<E> extArray;

    public ArrayStack(int capacity) {
        extArray = new ExtArray<E>(capacity);
    }

    public ArrayStack() {
        extArray = new ExtArray<E>();
    }

    //O(1)
    public void push(E e) {
        extArray.addLast(e);
    }

    //O(1)
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
