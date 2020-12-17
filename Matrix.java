import java.util.ArrayList;
import java.util.List;

public class Matrix {

    /**
     * Matrix in snake pattern
     * 
     * @param args
     */
    public static void matrixSnake(int mat[][]) {

        for (int i = 0; i < mat.length; i++) {

            if (i % 2 == 1)
                for (int j = mat[0].length - 1; j >= 0; j--)
                    System.out.print(mat[i][j] + " ");

            else
                for (int j = 0; j < mat[0].length; j++)
                    System.out.print(mat[i][j] + " ");
        }
    }

    /**
     * Bounded Traversal
     * 
     * @param args
     */

    public static void boundedTraversal(int mat[][]) {

        int rows = mat.length;
        int cols = mat[0].length;

        // rows = 0
        if (rows == 1)
            for (int i = 0; i < cols; i++) {
                System.out.print(mat[0][i] + " ");
            }

        // cols = 0

        else if (cols == 1)
            for (int i = 0; i < rows; i++) {
                System.out.print(mat[i][0] + " ");
            }

        else {

            for (int i = 0; i < cols; i++)
                System.out.print(mat[0][i] + " ");

            for (int i = 1; i < rows; i++)
                System.out.print(mat[i][cols - 1] + " ");

            for (int i = cols - 2; i > +0; i--)
                System.out.print(mat[rows - 1][i] + " ");

            for (int i = rows - 1; i >= 1; i--)
                System.out.print(mat[i][0] + " ");

        }

    }

    public static void printMatrix(int mat[][]) {
        System.out.println();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {

                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * Rotate matrix by 90deg
     * 
     * @param args
     */

    public static int[][] rotateMatrix(int mat[][]) {

        int rows = mat.length;
        int cols = mat[0].length;
        int rotationMat[][] = new int[cols][rows];

        for (int i = cols - 1, l = 0; i >= 0 && l < rows; i--, l++) {

            for (int j = 0, m = 0; j < rows && m < cols; j++, m++) {
                // System.out.print(mat[j][i]+" ");
                rotationMat[l][m] = mat[j][i];
            }
            System.out.println();
        }

        return rotationMat;
    }

    /**
     * Spiral traversal of matrix
     * 
     * @param args
     */

    public static void spiralTraversal(int mat[][]) {

        int rows = mat.length;
        int cols = mat[0].length;

        if (rows == 1)
            for (int i = 0; i < cols; i++)
                System.out.print(mat[0][i] + " ");

        else if (cols == 1)
            for (int i = 0; i < cols; i++)
                System.out.print(mat[i][0] + " ");

        else {

            int top = 0, left = 0, bottom = rows - 1, right = cols - 1;

            while (top <= bottom && left <= right) {

                for (int i = left; i <= right; i++)
                    System.out.print(mat[top][i] + " ");

                top++;

                for (int i = top; i <= bottom; i++)
                    System.out.print(mat[i][right] + " ");

                right--;

                for (int i = right; i >= left; i--)
                    System.out.print(mat[bottom][i] + " ");

                bottom--;

                for (int i = bottom; i >= top; i--)
                    System.out.print(mat[i][left] + " ");

                left++;
            }

        }

    }

    /**
     * Search in matrix which is row wise and column wise sorted. O(R+C) Begin from
     * top right corner x - key is smaller move left x - bigger move down else return x
     * 
     * @param args
     */

    public static List<Integer> searchInSortedMatrix(int mat[][], int k) {

        int rows = mat.length;
        int cols = mat[0].length;
        List<Integer> ans  = new ArrayList<Integer>();

        int i = 0;
        int j = cols-1;

        while (i < rows && j >= 0) {

            if (mat[i][j] == k) {
               ans.add(i);
               ans.add(j);
               return ans;
            }

            else if (k < mat[i][j])
                j--;

            else if (k > mat[i][j])
                i++;

        }

        return ans;

    }

    public static void main(String[] args) {

        int mat[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

        
        System.out.println(searchInSortedMatrix(mat, 8));
        
        //spiralTraversal(mat);

        // printMatrix(mat);
        // int ans[][] = rotateMatrix(mat);
        // printMatrix(ans);
        // matrixSnake(mat);
        // boundedTraversal(mat);
    }
}
