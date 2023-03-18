package volatile_task;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NicknamesGen {

    public static AtomicInteger beautyThree = new AtomicInteger(0);
    public static AtomicInteger beautyFour = new AtomicInteger(0);
    public static AtomicInteger beautyFive = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < texts.length; i++) {
            String myStr = texts[i];

            ex.execute(() -> {
                boolean flag = true;
                for (int j = 0; j < myStr.length() - 1; j++) {
                    if (myStr.charAt(j) != myStr.charAt(j + 1)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    addBeauty(myStr.length());
                }
            });

            ex.execute(() -> {
                boolean flag = true;
                for (int j = 0; j < myStr.length() / 2; j++) {
                    if (myStr.charAt(j) != myStr.charAt(myStr.length() - j - 1)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    addBeauty(myStr.length());
                }
            });

            ex.execute(() -> {
                boolean flag = true;
                for (int j = 0; j < myStr.length() - 1; j++) {
                    if (myStr.charAt(j) > myStr.charAt(j + 1)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    addBeauty(myStr.length());
                }
            });
        }

        ex.shutdown();
        ex.awaitTermination(10, TimeUnit.MINUTES);

        System.out.println("Beautiful words with length of 3: " + beautyThree.get() + " pcs.");
        System.out.println("Beautiful words with length of 4: " + beautyFour.get() + " pcs.");
        System.out.println("Beautiful words with length of 5: " + beautyFive.get() + " pcs.");
    }

    public static void addBeauty(int len) {
        switch (len) {
            case 3:
                beautyThree.getAndIncrement();
                break;
            case 4:
                beautyFour.getAndIncrement();
                break;
            case 5:
                beautyFive.getAndIncrement();
                break;
        }
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
