package org.dsa.binarytree;

import java.util.HashMap;
import java.util.Map;

public class BTFromInAndPostOrder {
    public static void main(String[] args) {
        int[] inorder = {2,6,3,9,5,10};
        int[] postorder = {2,9,3,6,10,5};

        System.out.println(getTreeFromPostorderAndInorder(postorder, inorder));
    }

    public static TreeNode getTreeFromPostorderAndInorder(int[] postOrder, int[] inOrder) {
        Map<Integer, Integer> inMap = new HashMap<>();

        for(int i=0; i<inOrder.length; i++) {
            inMap.put(inOrder[i], i);
        }

        TreeNode root = buildTree(inOrder, 0, inOrder.length - 1, postOrder, 0, postOrder.length -1, inMap);

        return root;

    }

    public static TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd, Map<Integer, Integer> inMap) {
        if(inStart > inEnd || postStart > postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);

        int inRoot = inMap.get(postorder[postEnd]);
        int numsLeft = inRoot - inStart;

        root.left = buildTree(inorder, inStart, inRoot - 1, postorder, postStart, postStart + numsLeft - 1, inMap);
        root.right = buildTree(inorder, inRoot + 1, inEnd, postorder, postStart + numsLeft, postEnd - 1, inMap);

        return root;
    }
}
