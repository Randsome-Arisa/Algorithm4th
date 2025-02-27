import java.util.Scanner;
//矩阵的转置（行列转化）
public class EX1_1_13 {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int N = 2;
        int M = 3;
        int[][] matrix = new int[N][M];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < M; j++)
                matrix[i][j] = scan.nextInt();
        System.out.println("矩阵：");
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++)
                System.out.print(matrix[i][j]);
            System.out.println();
        }
        System.out.println("转置：");
        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++)
                System.out.print(matrix[j][i]);
            System.out.println();
        }
        scan.close();
    }
}