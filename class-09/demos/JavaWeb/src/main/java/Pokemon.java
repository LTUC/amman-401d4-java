import java.util.List;

public class Pokemon {
    private int count;
    private String next;
    private String previous;
    private List<PokeData> results;

//    public class PokeData {
//        private String name;
//        private String url;
//
//        public String getName() {
//            return name;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        @Override
//        public String toString() {
//            return "PokeData{" +
//                    "name='" + name + '\'' +
//                    ", url='" + url + '\'' +
//                    '}';
//        }
//    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<PokeData> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "count=" + count +
                '}';
    }
}
