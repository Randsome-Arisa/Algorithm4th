/*
输入N，返回不大于log2(N)的最大整数
 */

import java.util.Scanner;
public class EX1_1_14 {
    public static int lg(int N){
        // 假设结果是X，也即 2^X <= N
        int x = 0;
        int pow = 1;
        while(pow <= N){
            x++;
            pow <<= 1;
        }
        return x-1;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = lg(scan.nextInt());
        System.out.println(n);
        scan.close();
    }
}