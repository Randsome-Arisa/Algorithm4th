/*
输入一个int数组
找到一个局部最小元素，即a[i]满足a[i] < a[i-1] && a[i] < a[i+1]
 */

import edu.princeton.cs.algs4.*;

public class EX1_4_18 {
    private static void minimum(int[] a) {
        int i = a.length / 2;
        while (i >= 0 && i < a.length) {
            if (a[i] > a[i - 1])
                i--;
            else if (a[i] > a[i + 1])
                i++;
            else {
                StdOut.println(a[i]);
                return;
            }
        }
        StdOut.println("no such number");
    }
    public static void main(String[] args) {
        int N = 5;
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(100);
        StdOut.println("数组：");
        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");
        minimum(a);
    }
}
