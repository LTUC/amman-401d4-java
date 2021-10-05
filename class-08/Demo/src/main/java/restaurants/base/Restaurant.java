package restaurants.base;

import review.Review;
import java.util.HashSet;
import java.util.Set;

public abstract class Restaurant {

    private int stars;

    private final String name;
    private final String priceCat;

    private Set<Review> reviews;

    public Restaurant(String name, String priceCat) {
        this.name = name;
        this.priceCat = priceCat;
        reviews = new HashSet<>();
    }

    public int getStars() {
        return stars;
    }

    public String getName() {
        return name;
    }

    public String getPriceCat() {
        return priceCat;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public void addReview(Review review) {
        getReviews().add(review);
        setStars(calculateRating());
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    protected int calculateRating() {
        int starSum = 0;

        for (Review review :
                reviews) {
            starSum += review.getStars();
        }

        return starSum / reviews.size();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "stars=" + stars +
                ", name='" + name + '\'' +
                ", priceCat='" + priceCat + '\'' +
                '}';
    }
}
