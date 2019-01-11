package com.imooc.day02_Stack;

public interface ExtStack<E> {
    void push(E e);
    E pop();
    E peek();
    int getSize();
    boolean isEmpty();
}
