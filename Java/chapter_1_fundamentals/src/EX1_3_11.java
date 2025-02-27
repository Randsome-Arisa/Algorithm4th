/********************
 * 求值一个后序表达式
 *******************/
import java.util.Scanner;
public class EX1_3_11 {
    public static boolean isOperator(String s){
        return (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }
    public static void EvaluatePostfix(){
        Scanner scan = new Scanner(System.in);
        String s = "";
        Stack<String> value = new Stack<String>();
        while(scan.hasNext()){
            s = scan.next();
            if(isOperator(s)){
                //遇到运算符
                double second = Double.parseDouble(value.pop()); //第二个运算数
                double first = Double.parseDouble(value.pop());  //第一个运算数
                String temp;
                if(s.equals("+"))
                    temp = first + second + "";
                else if(s.equals("-"))
                    temp = first - second + "";
                else if(s.equals("*"))
                    temp = first * second + "";
                else
                    temp = first / second + "";
                value.push(temp);
            }
            else{
                //数字时
                value.push(s);
            }
        }
        double result = Double.parseDouble(value.pop());
        System.out.println(result);
        scan.close();
    }
    public static void main(String[] args){
        EvaluatePostfix();
    }
}