package restaurants;

import restaurants.base.Restaurant;
import restaurants.chains.PopEyes;

public class PopEyesAbdoun extends Restaurant implements PopEyes {
    public PopEyesAbdoun(String name, String priceCat) {
        super(name, priceCat);
    }

    @Override
    public void iceCreamMachines() {

    }
}
