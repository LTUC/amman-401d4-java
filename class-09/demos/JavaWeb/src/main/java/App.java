import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import java.net.URL;

public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Web");
        // MalformedURLException and FileNoFoundException inherit from IOException

        String url = "https://pokeapi.co/api/v2/pokemon?limit=100&offset=200";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // HTTP CONNECT VERB
            // DELETE PUT POST GET OPTIONS HEAD PATCH
//            connection.setRequestMethod("GET");

            System.out.println(connection);

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // this data is the JSON
            String data = bufferedReader.readLine();
            System.out.println(data);

            bufferedReader.close();

            Gson gson = new Gson();
            Pokemon pokemon = gson.fromJson(data, Pokemon.class);
            System.out.println("the count is >>> " + pokemon);
            System.out.println("the previous is >>> " + pokemon.getPrevious());
            System.out.println("the results is >>> " + pokemon.getResults());
            System.out.println("The first pokemon is >>> " + pokemon.getResults().get(0).getUrl());
            System.out.println("The first pokemon is >>> " + pokemon.getResults().get(0).getName());
        } else {
            System.out.println("Request unable to processed");
        }
    }
}
