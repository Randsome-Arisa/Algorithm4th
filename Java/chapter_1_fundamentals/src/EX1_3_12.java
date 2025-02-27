/*************
 * 接受一个String栈作为参数并返回其一个副本
 ************/
public class EX1_3_12 {
    public static Stack<String> copy(Stack<String> source) {
        Stack<String> temp = new Stack<String>();
        for(String s : source){
            temp.push(s);
        }
        Stack<String> backup = new Stack<String>();
        for(String s : temp){
            backup.push(s);
        }
        return backup;
    }
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("hello");
        stack.push("world");
        Stack<String> newone = copy(stack);
        while(!newone.isEmpty())
            System.out.println(newone.pop());
    }
}