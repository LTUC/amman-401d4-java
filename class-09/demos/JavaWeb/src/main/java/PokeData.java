public class PokeData {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "PokeData{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}