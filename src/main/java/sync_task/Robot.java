package sync_task;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Robot {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            es.execute(() -> {
                String route = generateRoute("RLRFR", 100);
                int c = 0;
                for (int j = 0; j < 100; j++) {
                    if (route.charAt(j) == 'R') {
                        c++;
                    }
                }

                synchronized (sizeToFreq) {
                    if (sizeToFreq.containsKey(c)) {
                        sizeToFreq.replace(c, sizeToFreq.get(c), sizeToFreq.get(c) + 1);
                    } else {
                        sizeToFreq.put(c, 1);
                    }
                }
            });
        }

        es.shutdown();

        boolean finished = es.awaitTermination(1, TimeUnit.MINUTES);

        int most = 0;
        int c = -1;
        for(Map.Entry<Integer, Integer> en : sizeToFreq.entrySet()){
            if(en.getValue() > c){
                c = en.getValue();
                most = en.getKey();
            }
        }
        sizeToFreq.remove(most);
        System.out.println("The most frequent repeat of " + most + " (met " + c + " times)");
        System.out.println("Other sizes:");
        for(Map.Entry<Integer, Integer> en : sizeToFreq.entrySet()){
            System.out.printf("- %d (%d times)\n", en.getKey(), en.getValue());
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
