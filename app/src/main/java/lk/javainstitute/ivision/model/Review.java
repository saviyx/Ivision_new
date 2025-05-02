package lk.javainstitute.ivision.model;


public class Review {
    private String id;
    private String userName;
    private float rating;
    private String reviewText;
    private com.google.firebase.Timestamp timestamp;

    public Review() {}

    public Review(String userName, float rating, String reviewText) {
        this.userName=userName;
        this.rating=rating;
        this.reviewText=reviewText;
        this.timestamp=com.google.firebase.Timestamp.now();

    }


}
