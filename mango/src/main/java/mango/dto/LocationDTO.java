package mango.dto;

public class LocationDTO {

    private String address;

    private Float latitude;

    private Float longitude;

    public LocationDTO(String address, Float latitude, Float longitude){
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
