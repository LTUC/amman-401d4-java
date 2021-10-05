package restaurants;

import restaurants.base.Restaurant;
import restaurants.chains.MCDonald;

public class McDonaldIstikal extends Restaurant implements MCDonald {
    public McDonaldIstikal(String name, String priceCat) {
        super(name, priceCat);
    }

    @Override
    public void playInPark() {
        System.out.println("Playing");
    }
}
