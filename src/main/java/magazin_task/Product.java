package magazin_task;

import java.util.Objects;
import java.util.Random;

public class Product implements Trackable, Refundable{

    private int prise;
    private String name;
    private TrackStages trackStage;
    private boolean isRefundable;

    Product(int prise, String name){
        this(prise, name, false);
    }

    Product(int prise, String name, boolean isRefundable){
        this.prise = prise;
        this.name = name;
        this.isRefundable = isRefundable;
        trackStage = TrackStages.NOT_ORDERED;
    }

    public String getName(){
        return name;
    }

    public int getPrise(){
        return prise;
    }

    public void ordered(){
        trackStage = TrackStages.PROCESSING;
    };

    @Override
    public TrackStages track() {
        Random rand = new Random();
        if(rand.nextBoolean()){
            trackStage = trackStage.getNextStage();
        }
        return trackStage;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean isRefundable() {
        return isRefundable;
    }

    @Override
    public void setRefundable(boolean refundable) {
        isRefundable = refundable;
    }

    @Override
    public boolean equals(Object pd){
        if(pd == this){
            return true;
        }

        if(!(pd instanceof Product)){
            return false;
        }

        Product p = (Product) pd;

        return name.equals(p.getName());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }

    public Product clone(){
        return new Product(prise, name, isRefundable);
    }
}
