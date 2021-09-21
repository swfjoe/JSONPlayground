package com.example.JsonPlayground;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket {

    @JsonProperty("Passenger")
    private Passenger passenger;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @JsonProperty("Price")
    public void setPrice(Double price) {
        this.price = price;
    }
}