
public class Backtracking {

    /**
     * Rats in a maze problem: Valid moves (i+1,j); (i,j+1)
     */

    public static boolean solveMaze(int mat[][]) {

        int soln[][] = new int[mat.length][mat.length];

        if (solveMazeRec(mat, soln, 0, 0) == false)
            return false;

        else {
            printMatrix(soln);
            return true;
        }
    }

    private static boolean solveMazeRec(int mat[][], int soln[][], int i, int j) {

        if (i == mat.length - 1 && j == mat.length - 1) {
            soln[i][j] = 1;
            return true;
        }

        if (isSafe(mat, i, j) == true) {

            soln[i][j] = 1;

            if (solveMazeRec(mat, soln, i + 1, j) == true)
                return true;

            else if (solveMazeRec(mat, soln, i, j + 1) == true)
                return true;

            soln[i][j] = 0;
        }

        return false;
    }

    private static boolean isSafe(int mat[][], int i, int j) {

        return (i < mat.length && j < mat.length && mat[i][j] == 1);
    }

    private static void printMatrix(int mat[][]) {

        for (int i = 0; i < mat.length; i++) {

            for (int j = 0; j < mat[0].length; j++) {

                System.out.print(mat[i][j] + " ");
            }
        }
    }

    /************************ Rat maze problem ends *****************************/

    /************************* N-Queens problem *******************************/

    /**
     * n*n chessboard need to place n queens such that they can't kill each other.
     */

    public static boolean nQueens(int n) {

        int ans[][] = new int[n][n];

        if (nQueensRec(ans, n , 0) == false)
            return false;

        else {
            printMatrix(ans);
            return true;
        }
    }

    private static boolean nQueensRec(int ans[][], int N, int col) {
        
        if (col == N)
            return true;

        for (int i = 0; i < N; i++) {
           
            if (isSafeQueens(ans, N, i, col)) {
               
                ans[i][col] = 1;
               
                if (nQueensRec(ans, N, col + 1) == true)
                    return true;

                //backtrack
                ans[i][col] = 0;
            
            }
        }
        
        return false;
    }

    private static boolean isSafeQueens(int ans[][], int n, int row, int col) {

        int i, j;

        // column checking
        for (i = 0; i < col; i++)
            if (ans[row][i] == 1)
                return false;

        // diagonal checking
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (ans[i][j] == 1)
                return false;

        // opposite diagonal check
        for (i = row, j = col; j >= 0 && i < n; i++, j--)
            if (ans[i][j] == 1)
                return false;

        return true;
    }

    /**************************
     * N-Queens problem ends here
     *******************************/

    public static void main(String[] args) {

    }

}
