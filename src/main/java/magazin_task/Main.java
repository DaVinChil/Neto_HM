package magazin_task;

import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static final int SHOW_ALL_IN_STORE = 1;
    public static final int SHOW_CART = 2;
    public static final int SHOW_ORDERED = 3;
    public static final int ADD_TO_CART = 1;
    public static final int EXIT = 4;
    public static final int REFUND = 1;
    public static final int REMOVE = 1;
    public static final int BACK = 2;
    public static final int ORDER = 3;

    static String[] mainOptions = {
            "1. Show all in store",
            "2. Show cart",
            "3. Show ordered",
            "4. Exit"
    };

    static String[] cartOptions = {
            "1. Remove from cart",
            "2. Back",
            "3. Order item"
    };

    public static final String[] STORE_OPTIONS = {
            "1. Add to cart",
            "2. Back"
    };

    public static final String[] ORDERED_OPTIONS = {
            "1. Refund order",
            "2. Back"
    };

    public static final String ENTER_ANS = "Enter number of your choice: ";
    public static final String ENTER_INDEX_ITEM = "Enter number of item in the list: ";

    static ShoppingCart cart = new ShoppingCart();

    public static void main(String[] args) {
        showMainActions();
    }

    public static void showMainActions() {
        do {
            for (String option : mainOptions) {
                System.out.println(option);
            }
        } while(!mainChoice());
    }

    public static boolean mainChoice() {
        boolean endFlag = false;
        int ans = in.nextInt();
        switch (ans) {
            case SHOW_ALL_IN_STORE:
                showAllInStore();
                break;
            case SHOW_CART:
                showCart();
                break;
            case SHOW_ORDERED:
                showOrdered();
                break;
            case EXIT:
                endFlag = true;
                break;
        }
        return endFlag;
    }

    public static void showAllInStore() {
        Store.showAll();
        storeOptions();
    }

    public static void showOrdered() {
        cart.showOrdered();
        orderOptions();
    }

    public static void showCart() {
        cart.showCart();
        cartOptions();
    }

    public static void storeOptions() {
        for (String option : STORE_OPTIONS) {
            System.out.println(option);
        }

        System.out.println(ENTER_ANS);
        int ans = in.nextInt();
        switch (ans) {
            case ADD_TO_CART:
                addToCart();
                break;
            case BACK:
                break;
        }
    }

    public static void cartOptions() {
        for (String option : cartOptions) {
            System.out.println(option);
        }

        int ans = in.nextInt();
        switch (ans) {
            case BACK:
                break;
            case ORDER:
                orderItem();
                break;
            case REMOVE:
                removeFromCart();
                break;
        }
    }

    public static void orderOptions() {
        for (String option : ORDERED_OPTIONS) {
            System.out.println(option);
        }
        System.out.println(ENTER_ANS);
        int ans = in.nextInt();
        switch (ans) {
            case REFUND:
                refundItem();
                break;
            case BACK:
                break;
        }
    }

    public static void addToCart() {
        System.out.println(ENTER_INDEX_ITEM);
        int ans = in.nextInt();
        Store.addProductToCart(ans, cart);
    }

    public static void orderItem() {
        System.out.println(ENTER_INDEX_ITEM);
        int ans = in.nextInt();
        cart.order(ans);
    }

    public static void removeFromCart() {
        System.out.println(ENTER_INDEX_ITEM);
        int ans = in.nextInt();
        cart.removeFromCart(ans);
    }

    public static void refundItem() {
        System.out.println(ENTER_INDEX_ITEM);
        int ans = in.nextInt();
        cart.refundItem(ans);
    }

}
