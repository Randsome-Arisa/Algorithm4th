/*
 * 输入N和 M
 * N个人围成一圈（标记为从0~N-1号），从第一个人开始报数，报到M的人会被杀死，直到最后一个活下来。
 * 输出被杀死的顺序
 * 如：
 * 输入 7 2
 * 输出 1 3 5 0 4 2 6
 */
import java.util.Scanner;
public class Josephus {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int M = scan.nextInt();
        Queue<Integer> men = new Queue<Integer>();
        for(int i = 0; i < N; i++)
            men.enqueue(i);
        int k = 0;  //从第一个人开始报数
        while(!men.isEmpty()) {
            int temp = men.dequeue();
            if(++k % M == 0)
                System.out.print(temp + " ");
            else
                //不被杀死重新入队
                men.enqueue(temp);
        }
        System.out.println();
        scan.close();
    }
}