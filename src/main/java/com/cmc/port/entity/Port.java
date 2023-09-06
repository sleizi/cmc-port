package com.cmc.port.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Port implements Serializable {

    // I make this class simple to focus on parsing the json file

    @Id
    private String code;
    private String name;
    private String city;
    private String country;
    private String[] alias;
    private String[] regions;
    private double[] coordinates;
    private String province;
    private String timezone;
    private String[] unlocs;
}
