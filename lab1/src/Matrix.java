public class Matrix {
    static int[][] sum = new int[3][3];
    static int[][] prod = new int[3][3];
    public void addMatrix() {
        int[][] matrix1 = {
                {2, 3, 1},
                {7, 1, 6},
                {9, 2, 4}
        };
        int[][] matrix2 = {
                {8, 5, 3},
                {3, 9, 2},
                {2, 7, 3}
        };
        for (int i = 0; i<=2; i++) {
            for (int j = 0; j<=2; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
    }

    public void showAddMatrix() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                System.out.print(sum[i][j] + " ");
                if(j==2){
                    System.out.println();
                }
            }
        }
    }

    public void multiplyMatrix() {
        int[][] matrix1 = {
                {2, 3, 1},
                {7, 1, 6},
                {9, 2, 4}
        };
        int[][] matrix2 = {
                {8, 5, 3},
                {3, 9, 2},
                {2, 7, 3}
        };
        for (int i = 0; i<=2; i++) {
            for (int k = 0; k <= 2; k++) {
                int s = 0;
                for (int j = 0; j <= 2; j++) {
                    int p = 0;
                    p = matrix1[i][j] * matrix2[j][k];
                    s = s + p;
                }
                prod[i][k] = s;
            }
        }
    }

    public void showMultiplyMatrix() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                System.out.print(prod[i][j]+" ");
                if(j==2){
                    System.out.println();
                }
            }
        }
    }
}