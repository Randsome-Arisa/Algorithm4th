/*
输入N，返回一个NxN的数组a[][]
当i和j互质时，a[i][j]为true，否则为false
 */

import java.util.Scanner;

public class EX1_1_30 {
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b,a%b);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        boolean[][] a = new boolean[N][N];  // 默认为false
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (gcd(i, j) == 1)
                    a[i][j] = true;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                a[i][j] = a[j][i];
            }
        }
        // 打印
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(a[i][j] + "\t");
            System.out.println();
        }
        scan.close();
    }
}