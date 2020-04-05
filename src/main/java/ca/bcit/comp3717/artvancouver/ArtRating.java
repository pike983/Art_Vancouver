package ca.bcit.comp3717.artvancouver;

public class ArtRating {

    private String recordID;
    private float rating;
    private long numberRatings;

    public ArtRating() {}

    public ArtRating(String recordID) {
        this.recordID = recordID;
        this.rating = 0;
        this.numberRatings = 0;
    }

    public ArtRating(String recordID, float rating, long numberRatings) {
        this.recordID = recordID;
        this.rating = rating;
        this.numberRatings = numberRatings;
    }

    public String getRecordID() {
        return recordID;
    }

    public void setRecordID(String recordID) {
        this.recordID = recordID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getNumberRatings() {
        return numberRatings;
    }

    public void setNumberRatings(long numberRatings) {
        this.numberRatings = numberRatings;
    }
}
