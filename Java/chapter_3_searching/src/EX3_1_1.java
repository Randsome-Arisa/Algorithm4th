import java.util.Scanner;
public class EX3_1_1 {
    public static void main(String[] args) {
        SequentialSearchST<String, Double> st = new SequentialSearchST<String, Double>();
        st.put("A+", 4.33); st.put("A", 4.0); st.put("A-", 3.67);
        st.put("B+", 3.33); st.put("B", 3.0); st.put("B-", 2.67);
        st.put("C+", 2.33); st.put("C", 2.0); st.put("C-", 1.67);
        st.put("D", 1.0);   st.put("F", 0.0);
        Scanner scan = new Scanner(System.in);
        int n;  double total = 0;
        for(n = 0; scan.hasNext(); n++) {
            String grade = scan.next();
            double value = st.get(grade);
            total += value;
        }
        double gpa = total / n;
        System.out.println(gpa);
        scan.close();
    }
}