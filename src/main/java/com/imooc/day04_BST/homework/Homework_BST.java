package com.imooc.day04_BST.homework;

//1.二分查找树中添加元素
//2.二分查找树前中后层序遍历

import java.util.*;

public class Homework_BST<E extends Comparable<E>> {
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // add return root
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    private void add(Node node, E e) {
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

    private Node add2(Node node, E e) {
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

    // root --> left-tree --> right-tree
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    private void preOrderNR() {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    private void preOrder2(Node node) {
        if (node != null) {
            System.out.println(node.e);
            preOrder2(node.left);
            preOrder2(node.right);
        }
    }

    public void inOrder() {
        inOrder(root);
    }


    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        System.out.println(node.left);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder(){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);

            if(cur.left != null)
                queue.add(cur.left);
            if(cur.right != null)
                queue.add(cur.right);
        }
    }

    // find minimum
    public E minimum(){
        return minimum(root).e;
    }

    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    public E removeMin(){
        E res = minimum(root).e;
        root = removeMin(root);
        return res;
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // delete
    public void remove(E e){
        root = remove(root,e);
    }

    // ******
    private Node remove(Node node, E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        }
        else {// e == node.e
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右树均不为空的情况
            // 找到比待删除节点大的最小节点，即待删除节点左子树的最小节点（前驱或后继都行）
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }

    // 找到一棵树中比当前值大的节点中的最小节点
    public E floor(E e){
        return floor(root, e);
    }

    private E floor(Node node,E e){
        if(node == null)
            return null;
        return null;
    }


    // 找到一棵树中比当前值小的节点中的最大节点
    public E ceil(){
        return null;
    }

    public int rank(){
        return 0;
    }

    public E select(){
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    public void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    public String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Homework_BST<Integer> bst = new Homework_BST<Integer>();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> integers = new ArrayList<Integer>();
        while(!bst.isEmpty())
            integers.add(bst.removeMin());
        System.out.println(integers);

        for (int i = 1; i < integers.size(); i++) {
            if(integers.get(i-1) > integers.get(i))
                throw new IllegalArgumentException("Error");
        }
        System.out.println("removeMin complete");
    }
}
