import java.util.*;

public class ShowTree {
    static Integer[] res;

    public static String showTree(Integer[] arr) {
        StringBuilder result = new StringBuilder();

        int n = arr.length;
        res = new Integer[n];
        res[0] = arr[n / 2];

        buildTree(Arrays.copyOfRange(arr, 0, arr.length / 2), 0, 1);
        buildTree(Arrays.copyOfRange(arr, arr.length / 2 + 1, arr.length), 0, 2);

        int st = 0;
        int stages = log2(n + 1) - 1;
        for (int i = 1; i <= log2(n + 1); i++) {
            int j;
            for (j = st; j < n && j < st + ((int) Math.pow(2, i - 1)); j++) {
                for (int k = 0; k < Math.pow(2, stages) - 1; k++) { result.append("  "); }
                result.append(String.format("%2d  ", res[j]));
                for (int k = 0; k < Math.pow(2, stages) - 1; k++) { result.append("  "); }
            }
            st = j;
            stages--;
            result.append("\n");
        }

        return result.toString();
    }

    private static int log2(int N)
    {
        return (int)(Math.log(N) / Math.log(2));
    }

    private static void buildTree(Integer[] arr, int parent, int child) {
        if (arr.length == 0) {
            return;
        }

        res[parent * 2 + child] = arr[arr.length / 2];

        buildTree(Arrays.copyOfRange(arr, 0, arr.length / 2), parent * 2 + child, 1);
        buildTree(Arrays.copyOfRange(arr, arr.length / 2 + 1, arr.length), parent * 2 + child, 2);
    }

}
