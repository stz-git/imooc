package com.imooc.day02_Queue;

import com.imooc.day01_Array.HomeWorkArray;

/**
 * 使用动态数组实现队列
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E>{
    private HomeWorkArray<E> array;

    public ArrayQueue(int capacity){
        array = new HomeWorkArray<E>(capacity);
    }

    public ArrayQueue(){
        array = new HomeWorkArray<E>();
    }

    public int getSize() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    //O(1)
    public void enqueue(E e) {
        array.addLast(e);
    }
    //O(n):因为出队是将动态数组第一位的元素取出，后面所有的元素都会向前移一位，有一个遍历的过程
    public E dequeue() {
        return array.removeFirst();
    }
    //O(1)
    public E getFront() {
        return array.getFirst();
    }
    //O(1)
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < getSize(); i++) {
            res.append(array.get(i).toString());
            if (i != getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
