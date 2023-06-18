package com.mango.dto;

public class AvailableDriverDTO {
    private Integer id;
    private String name;
    private Float avgRating;
    private String image;
    private String carModel;

    public AvailableDriverDTO(Integer id, String name, Float avgRating, String image, String carModel){
        this.id = id;
        this.name = name;
        this.avgRating = avgRating;
        this.image = image;
        this.carModel = carModel;
    }


    
    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Float return the avgRating
     */
    public Float getAvgRating() {
        return avgRating;
    }

    /**
     * @param avgRating the avgRating to set
     */
    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    /**
     * @return String return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return String return the carModel
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * @param carModel the carModel to set
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }


    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
