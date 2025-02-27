/*
输入一个double数组
输出其中一对最接近的数（即相差的绝对值最小）
要求O(n)
 */

import edu.princeton.cs.algs4.*;
import java.util.Arrays;

public class EX1_4_16 {
    public static void main(String[] args) {
        int N = 10;
        double[] a = new double[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(100);
        Arrays.sort(a);
        double diff = Double.MAX_VALUE;
        double result = 0;
        for (int i = 0; i < N; i++) {
            int j = i;
            while (a[++j] == a[i]) {
                if (j == N)
                    break;
            }
            if ((a[j] - a[i]) < diff) {
                diff = a[j] - a[i];
                result = a[i];
            }
            i = j;
        }
        StdOut.println("数组：");
        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");
        StdOut.print(result);
        StdOut.print(result + diff);
    }
}
