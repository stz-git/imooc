package com.imooc.day04_BST;

public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();
        /*int[] array = {28, 23, 45, 67, 13, 2, 89};

        for (int i = 0; i < array.length; i++) {
            bst.add(array[i]);
        }*/

//        bst.preOrder();
//        System.out.println(bst);
//        bst.inOrder();
//        bst.postOrder();
//        bst.preOrderNR();
//        bst.levelOrder();
        bst.add(28);
        bst.add(33);
        bst.add(29);
        bst.add(26);
        bst.add(20);
        bst.add(30);
        bst.add(25);
//        bst.preOrder();
        bst.inOrder();
    }
}
