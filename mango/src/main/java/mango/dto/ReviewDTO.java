package mango.dto;

public class ReviewDTO {

    private Integer rating;

    private String comment;

    public ReviewDTO(Integer rating, String comment){
        this.rating = rating;
        this.comment = comment;
    }

    public ReviewDTO(){}

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
