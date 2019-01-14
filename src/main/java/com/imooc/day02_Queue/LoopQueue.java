package com.imooc.day02_Queue;
//循环队列
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];//循环队列会浪费一个空间
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return front == tail;
    }

    //入队：维护队尾tail的位置
    public void enqueue(E e) {
        //1.判断队列是否已满
        if ((tail + 1) % data.length == front) {
            resize(2 * getCapacity());
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++)
            newData[i] = data[(front + i) % data.length];
        data = newData;
        //重新维护队首和队尾的位置
        front = 0;
        tail = size;
    }

    //数组队列与循环队列主要的差距在出队上
    //出队：维护队首front的位置
    //O(1)
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        E ret = data[front];
        data[front] = null;

        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty");
        return data[front];
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d, capacity = %d\n",size,getCapacity()));
        res.append("front [");
        for (int i = 0; i < size; i++) {
            res.append(data[(i + front) % data.length].toString());
            if (i < size - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue.toString());
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue.toString());
            }
        }
    }
}
