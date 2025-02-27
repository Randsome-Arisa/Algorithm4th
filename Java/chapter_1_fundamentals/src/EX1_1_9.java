import java.util.Scanner;
//用二进制字符串表示一个正整数
class EX1_1_9{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String s = "";
        while(n > 0){
            s = (n % 2) + s;
            n >>= 1;
        }
        System.out.println(s);
        scan.close();
    }
}