package Tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class BST {

    public static void bfsTraversal(BinaryTree root) {

        Queue<BinaryTree> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {

            BinaryTree temp = q.poll();

            System.out.print(temp.val + " ");

            if (temp.left != null)
                q.add(temp.left);

            if (temp.right != null)
                q.add(temp.right);

        }

    }

    /**
     * Array to tree
     * 
     * @return
     */
    public static BinaryTree arrayToTree(Integer arr[]) {

        BinaryTree root = new BinaryTree(arr[0]);
        Queue<BinaryTree> q = new LinkedList<>();
        q.add(root);

        int i = 1;

        while (i < arr.length) {

            BinaryTree temp = q.poll();

            if (i < arr.length && arr[i] != null) {
                temp.left = new BinaryTree(arr[i]);
                q.add(temp.left);
            }

            i++;

            if (i < arr.length && arr[i] != null) {
                temp.right = new BinaryTree(arr[i]);
                q.add(temp.right);
            }

            i++;

        }

        return root;
    }

    public static BinaryTree insert(BinaryTree root, int key) {

        if (root == null) {
            root = new BinaryTree(key);
            return root;
        }

        if (key > root.val)
            root.right = insert(root.right, key);

        else if (key < root.val)
            root.left = insert(root.left, key);

        return root;
    }

    public static boolean search(BinaryTree root, int key) {

        if (root == null)
            return false;

        if (key > root.val)
            search(root.right, key);

        else if (key < root.val)
            search(root.left, key);

        else if (root.val == key)
            return true;

        return false;
    }

    /**
     * Deleting node in a binary search tree.
     */
    public static BinaryTree deleteNode(BinaryTree root, int key) {

        if (root == null)
            return null;

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }

        // condition for deleting node
        else {

            if (root.left == null)
                return root.right;

            else if (root.right == null)
                return root.left;

            else {

                BinaryTree successor = inorderSuccessor(root.right);
                root.val = successor.val;
                root.right = deleteNode(root.right, successor.val);

            }

        }

        return root;
    }

    /**
     * Returns the inorder successor of a node
     */
    private static BinaryTree inorderSuccessor(BinaryTree node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * Array to BST
     */
    public static BinaryTree arrayToBST(Integer arr[]) {

        BinaryTree root = new BinaryTree(arr[0]);

        for (int i = 0; i < arr.length; i++) {
            insert(root, arr[i]);
        }

        return root;
    }

    /**
     * Floor of a BST: To return the largest value present in tree which is smaller
     * than k
     */

    public static int floorBST(BinaryTree root, int k) {

        int floor = Integer.MIN_VALUE;

        while (root != null) {

            // search left subtree
            if (root.val > k) {

                root = root.left;
            }

            // search right subtree
            else if (root.val < k) {

                floor = root.val;
                root = root.right;
            }

            else {
                return root.val;
            }
        }

        return floor;
    }

    /**
     * Finding ceil in BST: Return smallest value which is greater than or equal to
     * k
     */

    public static int ceilBST(BinaryTree root, int k) {

        int ceil = Integer.MAX_VALUE;

        while (root != null) {

            // search left subtree
            if (root.val > k) {

                ceil = root.val;
                root = root.left;

            }

            // search right subtree
            else if (root.val < k) {

                root = root.right;
            }

            else {
                return root.val;
            }
        }

        return ceil;

    }

/**
 * Self balancing BST
 */

    public static void main(String[] args) {

        int a1[] = { 40, 20, 50, 10, 30, 80, 70, 90 };
        Integer a2[] = { 9, 8, null, 5, 10, null, 5, 6, null, 6 };

        Integer a3[] = { 70, 20, 30, 10, 51, 55, 48, 47, 42, 49, 80, 75, 88, 85 };

        // BinaryTree root = arrayToTree(a3);

        BinaryTree root = arrayToBST(a3);
        bfsTraversal(root);

        System.out.println(ceilBST(root, 50));
     
    }
}
