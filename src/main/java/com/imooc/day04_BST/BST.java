package com.imooc.day04_BST;

/**
 * 二分搜索树
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        E e;
        Node left;
        Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        /*if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add2(root, e);
        }*/
        root = add2(root, e);
    }

    // 向以node为根的二分搜索树中插入元素E，递归算法
    private void add(Node node, E e) {
        // 1.递归终止的条件
        if (e.equals(node.e))
            return;
        else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0)
            add(node.left, e);
        else
            add(node.right, e);
    }

    // 返回插入新节点后二分搜索树的根
    private Node add2(Node node, E e) {
        // 1.递归终止的条件
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add2(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add2(node.right, e);

        return node;
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        /*if (root == null)
            return false;
        else {
            return contains(root, e);
        }*/
        return contains2(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e，递归算法
    public boolean contains(Node node, E e) {
        //1.递归终止的条件
        if (e.equals(node.e))
            return true;
        else if (e.compareTo(node.e) < 0 && node.left == null)
            return false;
        else if (e.compareTo(node.e) > 0 && node.right == null)
            return false;

        if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    public boolean contains2(Node node,E e){
        if(node == null)
            return false;

        if(e.equals(node.e))
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains2(node.left, e);
        else //e.compareTo(node.e) > 0
            return contains2(node.right, e);
    }

    // 二分搜索树前序遍历
    public void preOrder(Node node){

    }
}
