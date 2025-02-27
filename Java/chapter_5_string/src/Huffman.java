import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
    // ASCII码需要的位数
    private static final int R = 256;

    // 进制实例化
    private Huffman() { }

    // Huffman结点
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;         // 权重（频率）
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    // Huffman压缩
    public static void compress() {
        // 输入
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        char[] input = s.toCharArray();

        // 统计每个字符的频率
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // 构造Huffman树
        Node root = buildTrie(freq);

        // 构造Huffman编码，保存在st表中
        String[] st = new String[R];
        buildCode(st, root, "");

        //writeTrie(root);

        // 编码
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    System.out.print("0");
                }
                else if (code.charAt(j) == '1') {
                    System.out.print("1");
                }
                else throw new IllegalStateException("Illegal state");
            }
        }

        scanner.close();
    }

    private static Node buildTrie(int[] freq) {

        // 用一个最小队列保存待合并结点
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (char c = 0; c < R; c++)
            if (freq[c] > 0)
                pq.add(new Node(c, freq[c], null, null));

        // 合并为Huffman树
        while (pq.size() > 1) {
            Node left  = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }
        return pq.poll();
    }


//    // write bitstring-encoded trie to standard output
//    private static void writeTrie(Node x) {
//        if (x.isLeaf()) {
//            BinaryStdOut.write(true);
//            BinaryStdOut.write(x.ch, 8);
//            return;
//        }
//        BinaryStdOut.write(false);
//        writeTrie(x.left);
//        writeTrie(x.right);
//    }

    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else {
            st[x.ch] = s;
        }
    }

    // 解码
    public static void expand() {

        // read in Huffman trie from input stream
        Node root = readTrie();

        // 待解码的字节数
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();

        // 解码
        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = scanner.nextBoolean();
                if (bit) x = x.right;
                else     x = x.left;
            }
            System.out.write(x.ch);
        }
        scanner.close();
    }


    private static Node readTrie() {
        Scanner scanner = new Scanner(System.in);
        boolean isLeaf = scanner.nextBoolean();

        if (isLeaf) {
            return new Node((char)scanner.nextByte(), -1, null, null);
        }
        else {
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }

    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
