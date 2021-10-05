import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import restaurants.McDonaldAbdoun;
import restaurants.McDonaldIstikal;
import restaurants.PopEyesAbdoun;
import restaurants.base.Restaurant;
import restaurants.chains.MCDonald;
import review.Review;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome Class");

        Restaurant macLoc1 = new McDonaldAbdoun("Mc Donalds Abdoun", "$$$");
        Restaurant macLoc2 = new McDonaldIstikal("Mc Donalds Istikal", "$$$");
        Restaurant popEyesLoc1 = new PopEyesAbdoun("Pop Eyes", "$$$$");

        Review mcDonaldsReview1 = new Review(5, "The food and service were very bad", "Jason");
        Review mcDonaldsReview2 = new Review(5, "The food and service were very bad", "Anas");
        Review mcDonaldsReview3 = new Review(5, "The food and service were very bad", "Naim");
        Review mcDonaldsReview4 = new Review(5, "The food and service were very bad", "Ghadeer");
        Review mcDonaldsReview5 = new Review(5, "The food and service were very bad", "Sanaa");
        Review mcDonaldsReview6 = new Review(5, "The food and service were very bad", "Jamal");
        Review mcDonaldsReview7 = new Review(5, "The food and service were very bad", "Rahaf");

        macLoc1.addReview(mcDonaldsReview1);
        macLoc1.addReview(mcDonaldsReview2);
        macLoc1.addReview(mcDonaldsReview3);
        macLoc1.addReview(mcDonaldsReview4);

        macLoc2.addReview(mcDonaldsReview4);
        macLoc2.addReview(mcDonaldsReview5);
        macLoc2.addReview(mcDonaldsReview6);
        macLoc2.addReview(mcDonaldsReview7);

        System.out.println(macLoc1);
        System.out.println("\n");
        System.out.println(macLoc2);

        MCDonald mcDonald = (MCDonald) macLoc1;
        mcDonald.playInPark();

        System.out.println("++++++++++++++++++++++++++++ GSON CONVERSION TO JSON ++++++++++++++++++++++++++++");
        String json = convertToJson(macLoc1);
        convertFromJson(json);

    }

    private static String convertToJson(Restaurant restaurant) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        System.out.println(gson.toJson(restaurant));
        return gson.toJson(restaurant);
    }

    private static void convertFromJson(String json) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        gson.fromJson(new BufferedReader(new FileReader("")), McDonaldAbdoun.class);

        Restaurant macLoc1 = gson.fromJson(json, McDonaldAbdoun.class);
        System.out.println("The Restaurant name is => " + macLoc1.getName());
        System.out.println("The Restaurant rating is => " + macLoc1.getStars());
    }
}
