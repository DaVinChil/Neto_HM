package second_task_singleton_logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Logger logger = Logger.getInstance();

        logger.log("Starting program");
        logger.log("Asking the user to enter the input data for the list");

        System.out.print("Enter size of list: ");
        int size = in.nextInt();

        System.out.print("Enter limit for list's elements: ");
        int limit = in.nextInt();

        logger.log("Creating and filling list");
        Random rand = new Random();
        List<Integer> list = rand.ints(size, 0, limit).boxed().collect(Collectors.toList());

        System.out.print("Here random list: " + list + "\n");

        logger.log("Asking the user to enter the input data for the filtering");
        System.out.print("Enter limit for filtering: ");
        int filterLimit = in.nextInt();

        Filter filter = new Filter(filterLimit);
        List<Integer> filteredList = filter.filterOut(list);

        logger.log("Displaying the result on the screen");
        System.out.println("Filtered list: " + filteredList);

        logger.log("Finishing program");
    }
}
