package org.dsa.binarySearchTree;

import org.dsa.binarytree.TreeNode;

public class LargestBSTinBT {
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return largestBSTSubtreeHelper(root).maxSize;

    }

    public NodeValue largestBSTSubtreeHelper(TreeNode root) {
        if(root == null) {
            return new NodeValue(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        NodeValue left = largestBSTSubtreeHelper(root.left);
        NodeValue right = largestBSTSubtreeHelper(root.right);

        if(left.maxNode < root.val && root.val < right.minNode) {
            return new NodeValue(left.maxSize + right.maxSize + 1, Math.max(root.val, right.maxNode), Math.min(root.val, left.minNode));
        }

        return new NodeValue(Math.max(left.maxSize, right.maxSize), Integer.MAX_VALUE, Integer.MIN_VALUE);
    }


}

class NodeValue {
    public int maxSize;
    int maxNode;
    int minNode;

    public NodeValue(int max, int maxNode, int min) {
        this.maxSize = max;
        this.maxNode = maxNode;
        this.minNode = min;
    }
}