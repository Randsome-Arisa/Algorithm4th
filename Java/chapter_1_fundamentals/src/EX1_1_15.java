/*
输入一个数组a[]和整数M。返回大小为M的数组result[]。
其中result[i]为i在a[]中出现的次数。
 */

import java.util.Scanner;
public class EX1_1_15 {
    public static int[] histogram(int[] a, int M){
        int[] result = new int[M];
        for(int i = 0; i < a.length; i++){
            if(a[i] < M)
                result[ a[i] ]++;
        }
        return result;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] a = new int[n];  //输入数组的大小
        for(int i = 0 ; i < n; i++)
            a[i] = scan.nextInt();
        System.out.println("input M");
        int M = scan.nextInt();
        int[] result = histogram(a, M);
        for(int i = 0; i < M; i++)
            System.out.println(result[i]);
        scan.close();
    }
}