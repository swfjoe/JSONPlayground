package com.example.JsonPlayground;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Flight {

    private Date departsOn;
    private List<Ticket> tickets = new ArrayList<>();

    public void setTickets(List<Ticket> ticketList) {
        this.tickets = ticketList;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm", timezone = "America/Chicago")
    public Date getDepartsOn() { return departsOn; }
    public void setDepartsOn(Date dateTime) { this.departsOn = dateTime; }
}