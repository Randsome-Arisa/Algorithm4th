/*
热还是冷？
随机生成一个1~N的整数：秘密数，猜测它,如果猜对则结束
相较于上一次猜测，更接近或不变时为热，否则为冷
 */

import edu.princeton.cs.algs4.*;
import java.lang.Math;

class HotColdGame {
    public final int N = 2000;  // 范围
    private int secret; // 秘密数
    private int diff;   //上一次猜测的差距
    HotColdGame() {
        secret = StdRandom.uniform(1, 2000);
        diff = Integer.MAX_VALUE;
    }
    // 如果热，返回1；冷返回-1；猜对返回0
    public int guess(int suppose) {
        if (suppose == secret)
            return 0;
        int temp = Math.abs(suppose - secret);
        if (temp > diff) {
            diff = temp;
            return -1;
        }
        else {
            diff = temp;
            return 1;
        }
    }
    public void play() {
        diff = Integer.MAX_VALUE;
        StdOut.println("guess a int number from 1 ~ "+ N);
        //StdOut.println(secret);
        StdOut.println("If you want to quit, just input -1");
        int suppose = StdIn.readInt();
        while (true) {
            if (suppose == -1) {
                StdOut.println("What a pity~");
                return;
            }
            int flag = guess(suppose);
            if (flag == 0)
                break;
            else if (flag == -1)
                StdOut.print("Cold!");
            else
                StdOut.print("Hot!");
            StdOut.println("\t Continue!");
            suppose = StdIn.readInt();
        }
        StdOut.println("done! The secret is " + secret);
    }
    public void autoGuess(int lo, int hi) {
        diff = Integer.MAX_VALUE;
        // 将区间均分，猜两次
        int flag;

        // 第一次在左半区间随机生成一个值
        int first = StdRandom.uniform(lo, (hi - lo) / 2 + lo);
        flag = guess(first);
        if (flag == 0) {
            StdOut.println("done! The secret is " + first);
            return;
        }
        else if (flag == 1)
            StdOut.println(first + " Hot!");
        else
            StdOut.println(first + " Cold!");

        // 第二次在右半区间随机生成一个值
        int second = StdRandom.uniform((hi - lo) / 2 + lo, hi);
        flag = guess(second);
        int mid = (second - first) / 2 + first; // 两次猜测的中间值
        if (flag == 0) {
            StdOut.println("done! The secret is " + second);
            return;
        }
        // 第二次猜测比第一次猜测热，说明更靠近右半区间，即区间更新为(mid, hi)
        else if (flag == 1) {
            StdOut.println(second + " Hot!");
            autoGuess(mid, hi);
        }
        // 同理
        else {
            StdOut.println(second + " Cold!");
            autoGuess(lo, mid);
        }
    }
}

public class HotOrCold {
    public static void main(String[] args) {
        HotColdGame game = new HotColdGame();
        game.play();
        StdOut.println();
        game.autoGuess(1, game.N);
    }
}
