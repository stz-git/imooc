package com.imooc.day01;

public class HomeWorkArray<E> {
    private E[] data;
    private int size;

    public HomeWorkArray(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public HomeWorkArray() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        newData = null;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        E ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        return ret;
    }

    public E removeLast(){
        E remove = remove(size - 1);
        return remove;
    }

    public E removeFirst(){
        E remove = remove(0);
        return remove;
    }


    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        data[index] = e;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }
        return data[index];
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array : size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i].toString());
            if (i < size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        HomeWorkArray homeWorkArray = new HomeWorkArray(5);
        homeWorkArray.addLast(new Student("xiaohong", 100));
        homeWorkArray.addLast(new Student("xiaolv", 92));
        homeWorkArray.addLast(new Student("xiaowang", 88));
        System.out.println(homeWorkArray.toString());
    }
}
