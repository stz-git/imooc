package com.imooc.day04_BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
    private boolean contains(Node node, E e) {
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

    private boolean contains2(Node node, E e) {
        if (node == null)
            return false;

        if (e.equals(node.e))
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains2(node.left, e);
        else //e.compareTo(node.e) > 0
            return contains2(node.right, e);
    }

    // 二分搜索树前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 二分搜索树前序遍历，递归算法
    private void preOrder(Node node) {
        //1.递归终止的条件
        if (node == null)
            return;
        //2.对节点的逻辑操作
        System.out.println(node.e);

        //3.遍历当前节点的左子树与右子树
        preOrder(node.left);
        preOrder(node.right);
    }

    private void preOrder2(Node node) {
        if (node == null) {
            System.out.println(node.e);
            preOrder2(node.left);
            preOrder2(node.right);
        }
    }

    // 二分搜索树前序遍历，压栈写法，基于系统栈的原理，记录遍历的位置
    public void preOrderNR(){
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while(!stack.isEmpty()){
            //1.将前序中先访问的当前节点弹栈并打印
            Node cur = stack.pop();
            System.out.println(cur.e);

            //2.将当前节点的两个子节点以右左的顺序压栈
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    // 二分搜索树中序遍历，所有数顺序排列
    public void inOrder(){
        inOrder(root);
    }

    // 二分搜索树中序遍历，递归算法
    private void inOrder(Node node){
        //1.递归终止的条件
        if(node == null)
            return;
        //2.先遍历当前节点的左子树
        inOrder(node.left);
        //3.访问当前节点
        System.out.println(node.e);
        //4.后遍历当前节点的右子树
        inOrder(node.right);
    }

    //TODO 二分搜索树中序遍历，非递归算法
    private void inOrderNR(){

    }

    // 二分搜索树后序遍历
    public void postOrder(){
        postOrder(root);
    }

    // 二分搜索树后序遍历，递归算法
    private void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //TODO 二分搜索树后序遍历，非递归算法
    private void postOrderNR(){

    }

    // 二分搜索树的层序遍历，即广度优先遍历，非递归算法，使用队列
    public void levelOrder(){
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        ///1.递归终止的条件，此时已经遍历到空节点
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);//先打印每个节点的左子树
//        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.right, depth + 1, res);//后打印每个节点的右子树
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

}
