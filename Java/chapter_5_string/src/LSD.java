// 低位优先排序
public class LSD {
    public static void sort(String[] a, int w) {
        int n = a.length;
        int R = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++)
                count[a[i].charAt(d) + 1]++;

            // compute cumulates
            for (int r = 0; r < R; r++)
                count[r+1] += count[r];

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++)
                a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        String[] a = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "10HV845", "4JZY524", "1ICK750", "3CIO720", "10HV845", "10HV845", "2RLA629", "2RLA629", "3ATW723"};
        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();

        // sort the strings
        sort(a, w);

        // print results
        for (int i = 0; i < n; i++)
            System.out.println(a[i]);
    }
}
