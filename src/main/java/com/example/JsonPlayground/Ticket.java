package com.example.JsonPlayground;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket {


    private Passenger passenger;
    private double price;

    public double getPrice() {
        return price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    //@JsonSetter("Passenger")
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    //@JsonSetter("Price")
    public void setPrice(double price) {
        this.price = price;
    }
}