//定义一个可比较的数据类型Time
public class Time implements Comparable<Time> {
    private final int minute;
    private final int second;
    public Time(int m, int s) {
        minute = m; second = s;
    }
    public int minute() {
        return minute;
    }
    public int second() {
        return second;
    }
    public int compareTo(Time that) {
        if(this.minute > that.minute)   return 1;
        if(this.minute < that.minute)   return -1;
        if(this.second > that.second)   return 1;
        if(this.second < that.second)   return -1;
        return 0;
    }
    public String toString() {
        return minute + ":" + second;
    }
}