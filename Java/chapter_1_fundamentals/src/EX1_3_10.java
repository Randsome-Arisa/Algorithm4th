/*
 * 中缀表达式转化为后缀表达式
 */
import java.util.Scanner;
public class EX1_3_10 {
    public static boolean isPrior(char a, char b){
        //1.任何运算符优先级高于(2.当a是乘除且b是加减时算优先级高
	    return (b == '(' || ((a == '*' || a == '/') && (b == '+' || b == '-')));
    }
    public static String InfixToPostfix(String infix){
        Stack<Character> stack = new Stack<Character>();    //保存操作符
        Queue<Character> postfix = new Queue<Character>();  //结果
        char c;
        for(int i = 0 ; i < infix.length(); i++){
            c = infix.charAt(i);
            if(Character.isDigit(c))
                //如果是数字直接入队
                postfix.enqueue(c);
            else if(c == '(')
                //'('入栈
                stack.push(c);
            else if(c == ')'){
                while(stack.peek() != '(')
                    postfix.enqueue(stack.pop());
                stack.pop();    //将'('出栈
            }
            else{
                //如果是运算符
                if(!stack.isEmpty() && !isPrior(c, stack.peek())){
                    //栈不空且当前运算符优先级低时
                    while(!isPrior(c, stack.peek())) {
                        //循环出栈直到当前运算符优先级高于栈顶
                        postfix.enqueue(stack.pop());
                        if(stack.isEmpty())
                            //栈空时停止出栈
                            break;
                    }
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty())
            postfix.enqueue(stack.pop());
        String result = "";
        while(!postfix.isEmpty())
            result += postfix.dequeue();
        return result;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String infix = scan.nextLine();
        String postfix = InfixToPostfix(infix);
        System.out.println(postfix);
        scan.close();
    }
}