package magazin_task;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public class Store {
    private static final Product[] products = {
            new Product(30, "Bubble gum"),
            new Product(87_982, "8K TV", true),
            new Product(6_000, "Microwave", true),
            new Product(872, "\"1984\" George Orwell", true),
            new Product(23_999, "Carmel medium sofa (RED)"),
            new Product(300, "Rubik's cube"),
            new Product(1699, "Terraria on PS5", true),
            new Product(2100, "Standard Yield Toner Cartridge")
    };

    public static void showAll(){
        int i = 1;
        for(Product pd : products){
            System.out.println(i + " - " + pd.getPrise() + "rub. " + pd);
            i++;
        }
    }

    private static Product getProduct(int i){
        i--;
        if(i >= 0 && i < products.length){
            return products[i];
        }
        return null;
    }

    public static void addProductToCart(int ind, ShoppingCart cart){
        cart.addToCart(getProduct(ind));
    }
}
