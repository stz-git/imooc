package com.imooc.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 动态数组：实现数组的增删改查
 * JDK中的数组并没有增和改的概念，因为数组在声明时长度是固定的，只能对对应索引位置上的数据进行赋值、修改、查询
 * 而我们的ExtArray实际上是类似于ArrayList，可以在数组的基础上进行add和remove，所以需要加入一个size的概念
 * <p>
 * 容量：capacity
 * 有效元素：size
 */
public class ExtArray {
    private int[] data;
    private int size;

    public ExtArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    //默认容量是10
    public ExtArray() {
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

    //在数组的末尾添加元素
    public void addLast(int e) {
        add(size, e);
    }

    //在数组的头添加元素
    public void addFirst(int e) {
        add(0, e);
    }

    //在数组的指定位置上添加元素
    public void add(int index, int e) {
        if (size == data.length)
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        //0<=index<=size(size=index时，在数组末尾添加元素)
        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed. Require index >= 0 and index < size.");
        //index之后的数据往后推
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    //更新数组指定位置上的元素
    public void set(int index, int e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        data[index] = e;
    }

    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public List<Integer> findAll(int e) {
        List<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                indexList.add(i);
            }
        }
        if (indexList.size() > 0) {
            return indexList;
        }
        return null;
    }

    //删除元素
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        int ret = data[index];
        //因为要大量的转移数组中的其他元素，也证明了ArrayList的增删效率慢的原因
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return ret;
    }

    public int removeFirst() {
        return remove(0);
    }

    public int removeLast() {
        return remove(size - 1);
    }

    //如果数组中存在元素e，只删除第一个
    public int removeElement(int e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
        return index;
    }

    //如果数组中存在元素e，只删除第一个
    public boolean removeAllElement(int e) {
        //不能一次查出该元素所有的位置，如果一次查出所有的index再遍历删除时，上一次删除都有可能导致剩余待删除元素索引的改变
        List<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                int index = remove(i);
                indexList.add(index);
                i--;//删掉这个索引上的元素，后面的元素会补上来，要对这个索引上的元素重新判断
            }
        }
        if (indexList.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        ExtArray extArray = new ExtArray();
        extArray.addLast(66);
        extArray.addLast(88);
        extArray.addLast(99);
        extArray.addLast(99);
        /*for (int i = 0; i < extArray.getSize(); i++) {
            System.out.println(extArray.get(i));
        }*/
        extArray.add(0, -1);
        System.out.println(extArray.toString());
//        System.out.println(extArray.get(2));
        List<Integer> indexList = extArray.findAll(99);
        System.out.println("indexList:"+indexList.toString());
        boolean flag = extArray.removeAllElement(99);
        System.out.println(flag);
        System.out.println(extArray.toString());
    }
}
