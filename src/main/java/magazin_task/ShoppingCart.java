package magazin_task;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingCart {
    private ArrayList<Product> cart = new ArrayList<>();
    private ArrayList<Product> ordered = new ArrayList<>();

    public void addToCart(final Product product){
        if(hasInCart(product)){
            System.out.println(product + " already in cart");
        } else if(hasInOrdered(product)){
            System.out.println(product + " already in ordered");
        } else {
            cart.add(product.clone());
        }
    }

    public boolean hasInCart(Product product){
        return cart.contains(product);
    }

    public boolean hasInOrdered(Product product){
        return ordered.contains(product);
    }

    public void showCart(){
        System.out.println("Your cart:");
        int i = 1;
        if(cart.size() == 0){
            System.out.println("Nothing in cart yet");
            return;
        }
        for(Product pd : cart){
            System.out.println(i + " - " + pd + " " + pd.getPrise() + " rub");
            i++;
        }
    }

    public void removeFromCart(int i){
        i--;
        if(i < cart.size() && i >= 0){
            System.out.println(cart.get(i) + " was removed from cart.");
            cart.remove(i);
        } else {
            System.out.println(i+1 + " item doesnt exist.");
        }
    }

    public void order(int i){
        i--;
        if(i >= 0 && i < cart.size()){
            System.out.println(cart.get(i) + " was ordered.");
            ordered.add(cart.get(i));
            cart.get(i).ordered();
            cart.remove(i);
        } else {
            System.out.println(i+1 + " item doesnt exist.");
        }
    }

    public void showOrdered(){
        System.out.println("Ordered:");
        int i = 1;
        if(ordered.size() == 0){
            System.out.println("Nothing ordered yet");
            return;
        }
        for(Product pd : ordered){
            System.out.print(i + " - " + pd);
            track(pd);
            i++;
        }
    }

    public void track(Trackable item){
        System.out.println("  ------>  " + item.track());
    }

    public void refundItem(int i){
        i--;
        if(i >= 0 && i < ordered.size()){
            refund(ordered.get(i));
        } else {
            System.out.println(i+1 + " item doesnt exist.");
        }
    }

    public void refund(Refundable item){
        if(item.isRefundable()){
            System.out.println(item + " has been returned.");
            ordered.remove(item);
        } else {
            System.out.println("Unfortunately you can not refund " + item);
        }
    }
}
