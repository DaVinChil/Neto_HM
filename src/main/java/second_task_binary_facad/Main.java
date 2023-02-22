package second_task_binary_facad;

public class Main {
    public static void main(String[] args) {
        BinOps bins = new BinOps();
        System.out.println(bins.sum("1010101", "1011001011110"));
        System.out.println(bins.mult("1010010101", "111011100100"));
    }
}
