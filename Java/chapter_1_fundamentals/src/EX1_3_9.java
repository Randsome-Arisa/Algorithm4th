/*
 * 输入：1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * 输出：( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
*/
import java.util.Scanner;
public class EX1_3_9 {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        Stack<String> value = new Stack<String>();
        Stack<String> operator = new Stack<String>();
        String s = "";
        while(scan.hasNext()){
            s = scan.next();
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
                //如果是操作符
                operator.push(s);
            else if(s.equals(")")){
                String v = value.pop();
                v = "(" + value.pop() + operator.pop() + v + s;
                value.push(v);
            }
            else{
                value.push(s);
            }
        }
        Stack<String> expression = new Stack<String>();
        while(!value.isEmpty())
            expression.push(value.pop());
        while(!expression.isEmpty()){
            System.out.print(expression.pop());
            System.out.print(operator.pop());
        }
        scan.close();
    }
}
//下面是github上另一种写法
/***************************************************
import java.util.Scanner;
public class test
{
    public static void main(String[] args)
    { 
        Scanner scan = new Scanner(System.in);
        Stack<String> ops  = new Stack<String>();
        Stack<String> vals = new Stack<String>();

        while (scan.hasNext())
        {
            String s = scan.next();
            
            if      (s.equals("("))               ;
            else if (s.equals("+") ||
                     s.equals("-") ||
                     s.equals("*") ||
                     s.equals("/") ||
                     s.equals("sqrt")) ops.push(s);
            else if (s.equals(")"))
            {
                String op = ops.pop();
                String v = vals.pop();
                
                if (op.equals("+") ||
                    op.equals("-") ||
                    op.equals("*") ||
                    op.equals("/"))
                    v = String.format("( %s %s %s )", vals.pop(), op, v);
                else if (op.equals("sqrt"))
                    v = String.format("( %s %s )", op, v);
                
                vals.push(v);
            }
            else vals.push(s);
            //else vals.push(((Double)Double.parseDouble(s)).toString());
        }
        System.out.println(vals.pop());
        scan.close();
    }
}
*******************************************************/