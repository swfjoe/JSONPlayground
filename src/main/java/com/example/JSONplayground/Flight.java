package com.example.JSONplayground;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Flight {

    @JsonProperty("Departs")
    private Date departsOn;
    @JsonProperty("Tickets")
    private List<Ticket> ticketList = new ArrayList<Ticket>();
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void addTicket(Ticket ticket) {
        ticketList.add(ticket);
    }

    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm", timezone = "America/Chicago")
    public Date getDepartsOn() { return departsOn; }
    public void setDepartsOn(Date dateTime) { this.departsOn = dateTime; }
}