package com.imooc.face.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Face_BST<E extends Comparable<E>> {
    private class Node {
        private E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
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

    // order:preOrder(stack)、inOrder、postOrder、levelOrder(queue)
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

    public void preOrderNR() {
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

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
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

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public void add(E e) {
        /*if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }*/
        root = addReturnRoot(root, e);
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
        else //e.compareTo(node.e) > 0
            add(node.right, e);
    }

    private Node addReturnRoot(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = addReturnRoot(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = addReturnRoot(node.right, e);
        return node;
    }


    public static void main(String[] args) {
        Face_BST<Integer> bst = new Face_BST<Integer>();
        bst.add(3);
        bst.add(15);
        bst.add(7);
        bst.add(9);
        bst.add(1);
        System.out.println(bst);
    }
}
