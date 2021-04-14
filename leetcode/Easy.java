package leetcode;

import java.util.PriorityQueue;

import Tree.BinaryTree;

public class Easy {

    public static int[] shuffle(int[] nums, int n) {

        int ans[] = new int[nums.length];

        int j = 0;
        int k = n + 1;
        for (int i = 0; i < nums.length; i++) {

            if (i % 2 == 0) {
                ans[i] = nums[j];
                j++;
            } else {
                ans[i] = nums[k];
                k++;
            }

        }

        return ans;
    }

    /**
     * https://leetcode.com/problems/running-sum-of-1d-array/
     * 
     * @param args
     */

    public int[] runningSum(int[] nums) {

        int sum[] = new int[nums.length];
        int temp = 0;
        for (int i = 0; i < sum.length; i++) {

            temp += nums[i];
            sum[i] = temp;
        }
        return sum;
    }

    /**
     * lcs recursion
     * 
     * @param args
     */

    public static int lcsRec(String a, String b, int m, int n) {

        if (m == 0 || n == 0)
            return 0;

        if (a.charAt(m) == a.charAt(n))
            return 1 + lcsRec(a, b, m - 1, n - 1);

        return Math.max(lcsRec(a, b, m - 1, n), lcsRec(a, b, m, n - 1));

    }

    public static int numDistinct(String a, String b) {

        int n = b.length();
        int m = a.length();

        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {

            dp[i][0] = 0;
        }

        for (int i = 0; i <= m; i++) {

            dp[0][i] = 0;
        }

        for (int i = 1; i <= m; i++) {

            if (b.charAt(0) == a.charAt(i - 1))
                dp[1][i] = dp[1][i - 1] + 1;

            else
                dp[1][i] = dp[1][i - 1];
        }

        for (int i = 2; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (b.charAt(i - 1) == a.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];

                else
                    dp[i][j] = dp[i][j - 1];
            }
        }

        return dp[n][m];

    }

    public static int minDistance(String a, String b) {

        int n = a.length();
        int m = b.length();

        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {

            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {

            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                else {

                    int case1 = dp[i - 1][j - 1];
                    int case2 = dp[i][j - 1];
                    int case3 = dp[i - 1][j];

                    dp[i][j] = 1 + Math.min(case1, Math.min(case2, case3));

                }

            }
        }

        return dp[n][m];

    }

    static void arrange(long arr[], int n) {

        long x = n;

        for (int i = 0; i < x; i++)
            arr[i] += (arr[(int) arr[i]] % x) * x;

        for (int i = 0; i < x; i++)
            arr[i] /= x;

        System.out.println(arr[0]);
    }

    class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    class NodeL {
        int data;
        NodeL next;

        NodeL(int key) {
            data = key;
            next = null;
        }
    }

    /**
     * Deletion in BST
     */

    public static Node deleteNode(Node root, int X) {
        return deleteNodeRec(root, X);
    }

    public static Node deleteNodeRec(Node root, int X) {

        if (root == null)
            return root;

        if (root.data < X) {
            root.right = deleteNodeRec(root.right, X);
        }

        else if (root.data > X) {
            root.left = deleteNodeRec(root.left, X);
        }

        else {

            if (root.left == null)
                return root.right;

            else if (root.right == null)
                return root.left;

            root.data = lca(root.right).data;
            root.right = deleteNodeRec(root.right, root.data);

            return root;
        }

        return root;

    }

    private static Node lca(Node root) {

        while (root.left != null) {

            root = root.left;
        }

        return root;
    }

    /**
     * Merge k-sorted lists
     * 
     * @param args
     */

    NodeL mergeKList(NodeL[] a, int N) {

        Node head = null;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {

            pq.add(a[i]);
        }

        int count = 0;

        while (!pq.isEmpty()) {

            Node temp = pq.poll();
            head = new Node(temp.data);
            if (temp.next != null)
                pq.add(temp.next);

        }

        return head;
    }

    public static void main(String[] args) {

        long a[] = { 4, 0, 2, 1, 3 };

        arrange(a, a.length);

        // String a = "abad";
        // String b = "abac";

        // System.out.println(minDistance(a, b));

    }
}
