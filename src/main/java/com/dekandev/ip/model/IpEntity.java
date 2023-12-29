package com.dekandev.ip.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class IpEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String requestedIpAddress;
    private String continentName;
    private String countryName;
    private String city;
    private LocalDateTime responseLocalTime; // to check whether I need to go to the api or not

    public IpEntity() {
    }

    public IpEntity(String id, String requestedIpAddress, String continentName, String countryName, String city, LocalDateTime responseLocalTime) {
        this.id = id;
        this.requestedIpAddress = requestedIpAddress;
        this.continentName = continentName;
        this.countryName = countryName;
        this.city = city;
        this.responseLocalTime = responseLocalTime;
    }

    public IpEntity(String requestedIpAddress, String continentName, String countryName, String city, LocalDateTime responseLocalTime) {
        this.requestedIpAddress = requestedIpAddress;
        this.continentName = continentName;
        this.countryName = countryName;
        this.city = city;
        this.responseLocalTime = responseLocalTime;
    }

    public String getId() {
        return id;
    }

    public String getRequestedIpAddress() {
        return requestedIpAddress;
    }

    public String getContinentName() {
        return continentName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }
}
