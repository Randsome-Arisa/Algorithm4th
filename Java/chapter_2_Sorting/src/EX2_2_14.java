/*import java.util.Queue;
public class EX2_2_14<Item>{
    public Queue<Item> QueueMerge(Queue<Item> a, Queue<Item> b) {
        int N = a.size() + b.size();
        Queue<Item> q;
        while(!a.isEmpty() && !b.isEmpty() {
            if(a.isEmpty())
                q.add(b.poll());
            else if(b.isEmpty())
                q.add(a.poll());
            else if(less(a.peek(), b.peek()))
                q.add(a.poll());
            else
                q.add(b.poll());
        }
        return q;
    }
}*/