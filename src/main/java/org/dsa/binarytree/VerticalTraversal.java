package org.dsa.binarytree;


import java.util.*;

/**
 *
 * 987. Vertical Order Traversal of a Binary Tree
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
 *
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
 * The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on
 * the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 *         Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 *         Column 2: Only node 7 is in this column.
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 *         Column -1: Only node 2 is in this column.
 *         Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 *         Column 2: Only node 7 is in this column.
 *
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 *         0 <= Node.val <= 1000
 */

class Triplet implements Comparator<Triplet>{
    TreeNode node;
    int row;
    int col;

    public Triplet(TreeNode node, int row, int col) {
        this.node = node;
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "node=" + node +
                ", row=" + row +
                ", col=" + col +
                '}';
    }

    @Override
    public int compare(Triplet t1, Triplet t2) {
        if(t1.col == t2.col) {
            if(t1.row == t2.row) {
                return t1.node.val - t2.node.val;
            } else {
                return t2.row - t2.row;
            }
        } else {
            return t1.col - t2.col;
        }
    }
}

public class VerticalTraversal {

    public static void main(String[] args) {
//        [1,2,3,4,6,5,7]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        VerticalTraversal v = new VerticalTraversal();
        System.out.println(v.verticalTraversal(root));
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        Map<Integer, List<Triplet>> inter = new TreeMap<>();
        List<List<Integer>> ans = new ArrayList<>();

        Queue<Triplet> q = new LinkedList<>();
        q.add(new Triplet(root, 0,0));

        while(!q.isEmpty()) {
            Triplet triplet = q.poll();
            TreeNode node = triplet.node;
            int row = triplet.row;
            int col = triplet.col;

            inter.computeIfAbsent(col, k -> new ArrayList<>()).add(triplet);

            if (node.left != null) {
                q.add(new Triplet(node.left, row+1, col-1));
            }
            if (node.right != null) {
                q.add(new Triplet(node.right, row+1, col+1));
            }

        }

        for (List<Triplet> triplet : inter.values()) {
            ans.add(new ArrayList<>());
            Collections.sort(triplet, (t1, t2) -> {
                if(t1.col == t2.col) {
                    if (t1.row == t2.row) {
                        return t1.node.val - t2.node.val;
                    } else {
                        return t1.row - t2.row;
                    }
                } else {
                    return t1.col - t2.col;
                }
            });
            for(Triplet t : triplet) {
                ans.get(ans.size() - 1).add(t.node.val);
            }
        }

        return ans;

    }

}
