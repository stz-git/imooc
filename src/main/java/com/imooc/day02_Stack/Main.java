package com.imooc.day02_Stack;

public class Main {
    private static double testStack(Stack stack, int opCount){
        long startTime = System.nanoTime();

        for (int i = 0; i < opCount; i++) {
            stack.push(i);
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 1000000;

        ArrayStack<Integer> integerArrayStack = new ArrayStack<Integer>();
        double time1 = testStack(integerArrayStack, opCount);
        System.out.println(time1);

        LinkedListStack<Integer> integerListStack = new LinkedListStack<Integer>();
        double time2 = testStack(integerListStack, opCount);
        System.out.println(time2);
    }
}
