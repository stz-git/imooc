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
 * <p>
 * 增删：O(n); 如果只对最后一个元素进行增删,时间复杂度还是O(n)?因为resize()?
 * 查改：如果已知索引,时间复杂度是O(1); 如果是未知索引,需要遍历,时间复杂度是O(n)
 * <p>
 * 如何分析addLast的时间复杂度？(均摊时间复杂度)
 * 假设capacity是n,n+1次addLast,触发resize,总共2n+1次基本操作
 * 约等于一次addLast,做了2次基本操作
 * 平均下来addLast的时间复杂度是O(1)级别的，与数组中元素个数是没有关系的
 * <p>
 * 模拟情景：数组已满，先进行addLast，需要进行一次扩容，时间复杂度是O(n)；紧接着进行removeLast，需要进行一次缩容，时间复杂度还是O(n)
 * 已经不满足均摊复杂度期望的n+1操作次进行一次扩容或者缩容，而是反复的扩缩
 * 这就是时间复杂度的震荡
 * 出现问题的原因：removeLast是resize的过于着急，扩容后我又变成了满的，至少让我在缩容后还有富余的时候再缩容吧
 * 解决方案：Lazy
 * 当size=capacity/4时，才将capacity减半
 */
public class ExtArray<E> {
    private E[] data;
    private int size;

    public ExtArray(int capacity) {
        data = (E[]) new Object[capacity];
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
    //O(1)
    public void addLast(E e) {
        add(size, e);
    }

    //在数组的头添加元素
    //O(n)
    public void addFirst(E e) {
        add(0, e);
    }

    //在数组的指定位置上添加元素
    //O(n)
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed. Require index >= 0 and index < size.");

        //扩容
        if (size == data.length) {
            resize(2 * data.length);
        }

        //index之后的数据往后推
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        size++;
    }

    //O(n)
    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        //转移原data中的元素
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        newData = null;
    }

    //O(1)
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    //O(1):支持随机方法
    //更新数组指定位置上的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        data[index] = e;
    }

    //O(n)
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {//值比较
                return true;
            }
        }
        return false;
    }

    //O(n)
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public List<Integer> findAll(E e) {
        List<Integer> indexList = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                indexList.add(i);
            }
        }
        if (indexList.size() > 0) {
            return indexList;
        }
        return null;
    }

    //删除元素
    //O(n)
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    //O(n)
    public E removeFirst() {
        return remove(0);
    }

    //O(1)
    public E removeLast() {
        return remove(size - 1);
    }

    //如果数组中存在元素e，只删除第一个
    public int removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
        return index;
    }

    //如果数组中存在元素e，只删除第一个
    public boolean removeAllElement(E e) {
        //不能一次查出该元素所有的位置，如果一次查出所有的index再遍历删除时，上一次删除都有可能导致剩余待删除元素索引的改变
        List<E> indexList = new ArrayList<E>();
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                E res = remove(i);
                indexList.add(res);
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
            res.append(data[i].toString());
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        ExtArray<Integer> extArray = new ExtArray<Integer>(10);
        extArray.addLast(66);
        extArray.addLast(88);
        extArray.addLast(99);
        extArray.addLast(99);
        extArray.addLast(199);
        extArray.add(0, -1);

        System.out.println(extArray.toString());

        extArray.remove(2);

        System.out.println(extArray.toString());
    }
}
