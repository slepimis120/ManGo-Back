package com.mango.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Rejection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "REASON", nullable = false)
    private String reason;

    @Column(name = "TIMEOFREJECTION", nullable = false)
    private Date timeOfRejection;

    @OneToOne
    @JoinColumn(name = "RIDEID",  referencedColumnName = "id")
    private Ride ride;

    public Rejection(String reason, Date timeOfRejection){
        this.reason = reason;
        this.timeOfRejection = timeOfRejection;
    }

}
