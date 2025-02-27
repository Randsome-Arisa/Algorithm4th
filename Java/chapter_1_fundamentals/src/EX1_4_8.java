//计算输入中相等的整数对的数量
import java.util.Arrays;
import java.util.Scanner;
public class EX1_4_8 {
    public static int equalNum(int[] a) {
        Arrays.sort(a);
        int cnt = 0;
        for(int i = 0; i < a.length; i++) {
            int j = i;
            while(a[++j] == a[i]) {
                if(j >= a.length)
                    break;
            }
            // 此时有j - i - 1个相同的值
            int offset = j - i - 1;
            // 有 从offset中任意选2个 对
            cnt += offset * (offset - 1) / 2;
            i = j;
        }
        return cnt;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Queue<Integer> q = new Queue<Integer>();
        while(scan.hasNext())
            q.enqueue(scan.nextInt());
        int[] a = new int[q.size()];
        int N = q.size();
        for(int i = 0; i < N; i++)
            a[i] = q.dequeue();
        int cnt = equalNum(a);
        System.out.println(cnt);
        scan.close();
    }
}