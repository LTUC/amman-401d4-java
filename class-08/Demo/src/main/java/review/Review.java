package review;

public class Review {

    private int stars;
    private String body;
    private String author;

    public Review(int stars, String body, String author) {
        this.stars = stars;
        this.body = body;
        this.author = author;
    }

    public int getStars() {
        return stars;
    }

    public void updateStars(int stars) {
        this.stars = stars;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Review{" +
                "stars=" + stars +
                ", body='" + body + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

