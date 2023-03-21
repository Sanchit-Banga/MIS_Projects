package com.example.newbusbookingsystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long busId;
    private String busName;
    private int seats;
    private String source;
    private String destination;
    private int occupied;
    private int jrnyHours;
    private int availDays;

    public BusModel(){}

    public BusModel(String busName, int seats, String source, String destination, int occupied, int jrnyHours, int availDays) {
        this.busName = busName;
        this.seats = seats;
        this.source = source;
        this.destination = destination;
        this.occupied = occupied;
        this.jrnyHours = jrnyHours;
        this.availDays = availDays;
    }

    @Override
    public String toString() {
        return "BusModel{" +
                "busId=" + busId +
                ", busName='" + busName + '\'' +
                ", seats=" + seats +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", occupied=" + occupied +
                ", jrnyHours=" + jrnyHours +
                ", availDays=" + availDays +
                '}';
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }

    public int getJrnyHours() {
        return jrnyHours;
    }

    public void setJrnyHours(int jrnyHours) {
        this.jrnyHours = jrnyHours;
    }

    public int getAvailDays() {
        return availDays;
    }

    public void setAvailDays(int availDays) {
        this.availDays = availDays;
    }
}
