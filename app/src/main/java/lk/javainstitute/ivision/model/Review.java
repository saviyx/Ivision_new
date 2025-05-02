package lk.javainstitute.ivision.model;


import com.google.firebase.Timestamp;

public class Review {
    private String id;
    private String userName;
    private float rating;
    private String reviewText;
    private com.google.firebase.Timestamp timestamp;

    public Review() {}

    public Review(String reviewText, float rating) {
        this.userName=userName;
        this.rating=rating;
        this.reviewText=reviewText;
        this.timestamp=com.google.firebase.Timestamp.now();

    }


    //gettes and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    public String getReviewText(){
        return reviewText;
    }
    public void setReviewText(String reviewText){
        this.reviewText=reviewText;

    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp){
        this.timestamp=timestamp;
    }
}
