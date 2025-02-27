/*************
 * 输入：[()]{}{[()()]()}
 * 输出：ture
 * 输入：[(])
 * 输入：false
*************/
import java.util.Scanner;
public class EX1_3_4 {
    public static boolean isMatched(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{')
                stack.push(c);
            else if( 
                ( c == ')' && (stack.isEmpty() || stack.pop() != '(') ) ||
                ( c == ']' && (stack.isEmpty() || stack.pop() != '[') ) ||
                ( c == '}' && (stack.isEmpty() || stack.pop() != '{') )
                )
                return false;
        }
        return stack.isEmpty();
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        if(isMatched(s))
            System.out.println("true");
        else
            System.out.println("false");
        scan.close();
    }
}