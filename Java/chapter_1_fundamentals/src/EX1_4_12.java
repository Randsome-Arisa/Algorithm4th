//输出两个有序int数组的所有公共元素（简单起见每个数组没有相同的值）
import java.util.Scanner;
import java.util.Arrays;
public class EX1_4_12 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        final int N = 5;
        int[] a = new int[N];
        int[] b = new int[N];
        for(int i = 0; i < N; i++)
            a[i] = scan.nextInt();
        for(int i = 0; i < N; i++)
            b[i] = scan.nextInt();
        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;  int j = 0;  //分别是a和b数组的“指针”
        while(i<=N && j<=N) {
            if(a[i] == b[j]) {
                System.out.println(a[i]);
                i++;    j++;
            }
            else if(a[i] > b[j])
                j++;
            else
                i++;
        }
        scan.close();
    }
}