package com.imooc.day03_LinkedList;

/**
 * ArrayList与LinedList的区别：
 * 1.ArrayList是基于动态数组实现，LinedList是基于链表实现
 * 2.对于随机访问的get和set，ArrayList要优于LinedList，因为ArrayList有索引可以直接定位，而LinedList需要移动指针
 * 3.对于add和remove，LinkedList要优于ArrayList
 * 因为LinkedList增删如果，所以只需要维护(Node.next=head)，时间复杂度是O(1)级别
 * 而ArrayList除了addLast(E e)，还有add(int index,E e)，后者需要移动数据，时间复杂度是O(n)级别
 *
 * 增删改查的时间复杂度全部是O(n)
 * @param <E>
 */
public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyhead;//虚拟头节点
    private int size;

    public LinkedList() {
        dummyhead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //在链表头添加元素
    //O(1)
    public void addFirst(E e) {
        add(0, e);
    }

    //在链表尾部添加元素
    //O(n)
    public void addLast(E e) {
        add(size, e);
    }

    //在链表指定位置添加元素
    //O(n/2)=O(n)
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyhead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

        prev.next = new Node(e, prev.next);
        size++;
    }

    //O(n)
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        Node cur = dummyhead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }
    //O(1)
    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size);
    }
    //O(n)
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        Node cur = dummyhead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        Node cur = dummyhead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LinkedList : size = %d\n", size));

//        Node cur = dummyhead.next;
//        while(cur != null){
//            res.append(cur.toString()+"->");
//            cur = cur.next;
//        }
        for (Node cur = dummyhead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }
    //O(n)
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        Node prev = this.dummyhead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }
    ////O(1)
    public E removeFirst(){
        return remove(0);
    }
    //O(n)
    public E removeLast(){
        return remove(size - 1);
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);
        linkedList.remove(3);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
