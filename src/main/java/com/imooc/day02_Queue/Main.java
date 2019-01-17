package com.imooc.day02_Queue;

import java.util.Random;

/**
 * 性能分析：数组队列与循环队列出队时的区别
 */
public class Main {
    private static double testQueue(Queue<Integer> queue, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            queue.dequeue();

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;

        double time1 = testQueue(new ArrayQueue<Integer>(), opCount);
        System.out.println("ArrayQueue time: " + time1 + "s");

        double time2 = testQueue(new LoopQueue<Integer>(), opCount);
        System.out.println("LoopQueue time: " + time2 + "s");

        double time3 = testQueue(new LinkedListQueue<Integer>(), opCount);
        System.out.println("LinkedListQueue time: " + time3 + "s");
    }
}
