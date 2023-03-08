import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Randoms implements Iterable<Integer> {
    Random rand;
    int min;
    int max;

    public Randoms(int min, int max){
        this.min = min;
        this.max = max;
        rand = new Random();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                int r = rand.nextInt(max-min+1);
                return min + r;
            }
        };
    }
}
