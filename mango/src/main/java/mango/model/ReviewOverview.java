package mango.model;

import jakarta.persistence.*;

@Entity
public class ReviewOverview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "VEHICLEREVIEW",  referencedColumnName = "id")
    private Review vehicleReview;

    @OneToOne
    @JoinColumn(name = "DRIVERREVIEW",  referencedColumnName = "id")
    private Review driverReview;

    @ManyToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;



    public ReviewOverview(Review vehicleReview, Review driverReview){
        this.vehicleReview = vehicleReview;
        this.driverReview = driverReview;
    }

    public ReviewOverview(){

    }


    public Review getVehicleReview() {
        return vehicleReview;
    }

    public void setVehicleReview(Review vehicleReview) {
        this.vehicleReview = vehicleReview;
    }

    public Review getDriverReview() {
        return driverReview;
    }

    public void setDriverReview(Review driverReview) {
        this.driverReview = driverReview;
    }
}
