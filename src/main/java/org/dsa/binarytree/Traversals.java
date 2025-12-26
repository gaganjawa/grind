package org.dsa.binarytree;

import java.util.*;

public class Traversals {
    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }
        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    };
    static class Node {
        TreeNode node;
        int i;
        Node(TreeNode treeNode, int num) {
            this.node = treeNode;
            this.i = num;
        }
    }
    static List<Integer> inorder = new ArrayList<>();
    static List<Integer> preorder = new ArrayList<>();
    static List<Integer> postorder = new ArrayList<>();
    static Stack<Node> stack = new Stack<>();
    public static List<List<Integer>> getTreeTraversal(TreeNode root) {
        // Write your code here.

        stack.push(new Node(root, 1));

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            if(node.i == 1) {
                preorder.add(node.node.data);
                if(node.node.left != null) {
                    stack.push(new Node(node.node.left, 2));
                }
            } else if(node.i == 2) {
                inorder.add(node.node.data);
                if(node.node.right != null) {
                    stack.push(new Node(node.node.right, 3));
                }
            } else {
                postorder.add(node.node.data);
            }
        }

        List<List<Integer>> traversals = new ArrayList<>();
        traversals.add(preorder);
        traversals.add(inorder);
        traversals.add(postorder);

        return traversals;

    }
}