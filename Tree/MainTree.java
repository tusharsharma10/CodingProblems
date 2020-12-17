package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import CustomList.ListNode;

public class MainTree {

    /**
     * BFS Traversal to print array
     * 
     * @param root
     */
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

    /**
     * Height of binary tree: Algorithm: Recursively find height of left subtree &
     * right subtree take the max of both and add 1 to it.
     */

    public static int heightOfTree(BinaryTree root) {

        if (root == null)
            return 0;

        return Math.max(heightOfTree(root.left), heightOfTree(root.right)) + 1;
    }

    /***
     * Print nodes K at a distance
     */

    public static void printNodesKAtDist(BinaryTree root, int k) {

        if (root == null)
            return;

        if (k == 0)
            System.out.print(root.val + " ");

        else {
            printNodesKAtDist(root.left, k - 1);
            printNodesKAtDist(root.right, k - 1);
        }
    }

    /**
     * Size of binary Tree
     */

    public static int sizeOfBinTree(BinaryTree root) {

        if (root == null)
            return 0;

        else
            return 1 + sizeOfBinTree(root.left) + sizeOfBinTree(root.right);

    }

    /**
     * Maximum value in binary tree
     */

    public static int maxVal(BinaryTree root) {

        if (root == null)
            return Integer.MIN_VALUE;

        else {

            return Math.max(root.val, Math.max(maxVal(root.left), maxVal(root.right)));

        }
    }

    /**
     * Print left most node in all levels. Recursive and Queue both are valid.
     */

    public static void printLeftNode(BinaryTree root) {

        if (root == null)
            return;

        if (root.left != null)
            System.out.print(root.left.val + " ");

        printLeftNode(root.left);
        printLeftNode(root.right);
    }

    /**
     * Children Sum Property
     * 
     * Children Sum Property is a property in which the sum of values of the left
     * child and right child should be equal to the value of their node if both
     * children are present. Else if only one child is present then the value of the
     * child should be equal to its node value. In this video, we discuss a
     * recursive function that takes the root node as a parameter and returns TRUE
     * if the Tree follows C.S.P. and FALSE if the Tree does not follow C.S.P.
     */

    public static boolean hasChildrenSum(BinaryTree root) {

        if (root == null)
            return true;

        else if (root.left == null && root.right == null)
            return true;

        int sum = 0;

        if (root.left != null)
            sum += root.left.val;

        if (root.right != null)
            sum += root.right.val;

        return (root.val == sum && hasChildrenSum(root.left) && hasChildrenSum(root.right));

    }

    /**
     * Check whether binary trees is balanced. Any node for which left subtree -
     * right subtree height > 1 then unbalanced O(N)
     */

    public static boolean isBalancedTree(BinaryTree root) {

        if (root == null)
            return true;

        int leftHt = root.left != null ? heightOfTree(root.left) : 0;
        int rightHt = root.right != null ? heightOfTree(root.right) : 0;

        return (Math.abs(leftHt - rightHt) <= 1 && isBalancedTree(root.left) && isBalancedTree(root.right));
    }

    /**
     * Maximum width: Maximum Width of Binary tree is the maximum number of nodes
     * present in a level of the Tree.
     */

    public static int maxWidth(BinaryTree root) {

        Queue<BinaryTree> q = new LinkedList<>();
        q.add(root);
        int maxWidth = 0;

        while (!q.isEmpty()) {

            BinaryTree temp = q.poll();

            maxWidth = Math.max(maxWidth, q.size());

            if (temp.left != null) {
                q.add(temp.left);
                maxWidth = Math.max(maxWidth, q.size());
            }

            if (temp.right != null) {
                q.add(temp.right);
                maxWidth = Math.max(maxWidth, q.size());
            }

        }

        return maxWidth;
    }

    /**
     * Binary tree to Doubly linked list using inorder traversal. Just Put 0 at the
     * beginning which can be removed very easily.
     */

    public static ListNode binTreeToDoublyList(BinaryTree root, ListNode head) {

        if (root == null)
            return head;

        // updating the head returned from base case.
        head = binTreeToDoublyList(root.left, head);

        head.next = new ListNode(root.val);
        head.next.prev = head;
        head = head.next;

        // updating the head returned from base case.
        head = binTreeToDoublyList(root.right, head);

        return head;
    }

    /**
     * Print link list
     * 
     * @param args
     */

    public static void printLinkList(ListNode head) {

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

    }

    /**
     * Given inorder and preorder traversal of binary tree COnstruct the binary tree
     */

    static int preIndex = 0;

    public static BinaryTree constructTree(int inorder[], int preorder[], int start, int end) {

        if (start > end)
            return null;

        BinaryTree root = new BinaryTree(preorder[preIndex]);
        preIndex++;

        int inIndex = 0;

        for (int i = start; i <= end; i++) {

            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }

        // really clever we have pivoted start we know that it is root.
        root.left = constructTree(inorder, preorder, start, inIndex - 1);
        root.right = constructTree(inorder, preorder, inIndex + 1, end);

        return root;
    }

    /**
     * Tree traversal in spiral form
     */

    public static void treeSpiralTraversal(BinaryTree root) {

        Queue<BinaryTree> q = new LinkedList<>();
        q.add(root);

        Stack<Integer> stack = new Stack<>();

        boolean reverse = false;

        while (!q.isEmpty()) {

            int count = q.size();

            for (int i = 0; i < count; i++) {
                BinaryTree temp = q.poll();
                if (reverse)
                    stack.push(temp.val);
                else
                    System.out.print(temp.val + " ");

                if (temp.left != null)
                    q.add(temp.left);
                if (temp.right != null)
                    q.add(temp.right);
            }

            if (reverse) {
                while (!stack.isEmpty()) {
                    System.out.print(stack.pop() + " ");
                }
            }

            reverse = !reverse;
        }

    }

    /**
     * Diameter of a binary tree: Longest path b/w two leaf nodes. For a node
     * caculate leftHeight + rightHeight + 1 the node for which above value is max
     * is diameter.
     */

    static int diameter = 0;

    public static int diameterOfTree(BinaryTree root) {

        if (root == null)
            return 0;

        int leftHt = heightOfTree(root.left);
        int rightHt = heightOfTree(root.right);

        diameter = Math.max(leftHt + rightHt + 1, diameter);

        diameterOfTree(root.left);
        diameterOfTree(root.right);

        return diameter;
    }

    /**
     * Lowest common ancestor of binary tree. Return the lowest level/nearest of
     * common ancestor of two given nodes.
     */

    public static BinaryTree lowestCommonAncestor(BinaryTree root, int n1, int n2) {

        if (root == null)
            return null;

        if (root.val == n1 || root.val == n2)
            return root;

        BinaryTree lca1 = lowestCommonAncestor(root.left, n1, n2);

        BinaryTree lca2 = lowestCommonAncestor(root.right, n1, n2);

        if (lca1 != null && lca2 != null)
            return root;

        if (lca1 != null)
            return lca1;

        else
            return lca2;
    }

    /**
     * Difficulty Level : Hard Burn a binary tree : return distance of the Farthest
     * node from a given node. https://ide.geeksforgeeks.org/V27JCdDLgY Algorithm:
     * 
     */

    static int res = 0;

    public static int burnTree(BinaryTree root, int leaf, int distance) {

        if (root == null)
            return 0;

        if (root.val == leaf) {

            distance = 0;
            return 1;
        }

        int lDist = -1;
        int rDist = -1;

        int lh = burnTree(root.left, leaf, lDist);
        int rh = burnTree(root.right, leaf, rDist);

        if (lDist != -1) {
            distance = lDist + 1;
            res = Math.max(res, rh + distance);
        }

        else if (rDist != -1) {
            distance = rDist + 1;
            res = Math.max(res, distance + lh);
        }

        return Math.max(lh, rh) + 1;
    }

    /**
     * Count of nodes in complete binary tree. Last level has to be filled from left
     * to right. can't add node at right by leaving a left node at same level. O(N)
     * using queue O(logn*logn)
     */

    public static int countNodes(BinaryTree root) {

        int leftHt = 0, rightHt = 0;
        BinaryTree temp = root;

       // calculating left height
        while (temp != null) {

            leftHt++;
            temp = temp.left;
        }

        temp = root;

        // calculating right height
        while (temp != null) {

            rightHt++;
            temp = temp.right;
        }

        if (leftHt == rightHt)
            return (int) Math.pow(2, leftHt) - 1;

        return 1 + countNodes(root.left) + countNodes(root.right);

    }

    /**
     * Main Method:
     */

    public static void main(String[] args) {

        // int arr[] = { 10, 20, 30, 40, 50, 60, 70, 80 };

        int a1[] = { 40, 20, 50, 10, 30, 80, 70, 90 };
        Integer a2[] = { 9, 8, null, 5, 10, null, 5, 6, null, 6 };

        Integer a3[] = { 10, 20, 60, 30, 80, null, null, 40, 50, null, 90, null, null, null, null, null, 18 };

        BinaryTree root = arrayToTree(a3);

        System.out.println(diameterOfTree(root));

        // treeSpiralTraversal(root);

        // BinaryTree r1 = constructTree(a1, a2,0,a1.length-1);

        // ListNode core = new ListNode(0);
        // ListNode head = binTreeToDoublyList(root, core);

        // printLinkList(core);

        // System.out.println(maxWidth(root));
        // System.out.println(isBalancedTree(root));

        // System.out.println(hasChildrenSum(root));

        // System.out.println(root);

        // System.out.println(hasChildrenSum(root));
        // printLeftNode(root);
        // System.out.println(maxVal(root));

        // printNodesKAtDist(root, 2);

        // bfsTraversal(root);
        // System.out.println("Height of tree: "+heightOfTree(root));
        // BinaryTree root = new BinaryTree(10);

    }
}
