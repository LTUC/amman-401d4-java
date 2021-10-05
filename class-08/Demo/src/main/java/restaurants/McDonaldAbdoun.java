package restaurants;

import restaurants.base.Restaurant;
import restaurants.chains.MCDonald;

public class McDonaldAbdoun extends Restaurant implements MCDonald {

    private int playPark;

    public McDonaldAbdoun(String name, String priceCat) {
        super(name, priceCat);
    }

    @Override
    public void playInPark() {
        System.out.println("Playing");
    }

    @Override
    public String toString() {
        return "McDonaldAbdoun{" +
                "playPark=" + playPark +
                "playPark=" + super.toString() +
                '}';
    }
}
