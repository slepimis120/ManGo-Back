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

    public LocationDTO(){}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}