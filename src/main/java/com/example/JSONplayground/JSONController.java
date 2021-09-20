package com.example.JSONplayground;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.*;

@RestController
@RequestMapping("/flights")
public class JSONController {

    @GetMapping("")
    public List<Flight> getFlights() {
        List<Flight> flightList = new ArrayList<>();

        Flight flight1 = new Flight();
        Flight flight2 = new Flight();

        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("John");
        passenger1.setLastName("Smith");

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(175d);
        ticket1.setPassenger(passenger1);

        Passenger passenger2 = new Passenger();
        passenger2.setFirstName("Mar");
        passenger2.setLastName("Camel");

        Ticket ticket2 = new Ticket();
        ticket2.setPrice(450d);
        ticket2.setPassenger(passenger2);

        flight1.setDepartsOn(new Date(Calendar.YEAR, Calendar.SEPTEMBER, Calendar.DATE, Calendar.HOUR, Calendar.MINUTE));
        flight2.setDepartsOn(new Date(2021 - 1900, Calendar.SEPTEMBER, 21, 9, 45));
        flight1.addTicket(ticket1);
        flight2.addTicket(ticket2);

        flightList.add(flight1);
        flightList.add(flight2);

        return flightList;
    }

    @GetMapping("/flight")
    public Flight getFlight() {

        Flight flight1 = new Flight();

        Passenger passenger1 = new Passenger();
        passenger1.setFirstName("John");
        passenger1.setLastName("Smith");

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(175d);
        ticket1.setPassenger(passenger1);

        Passenger passenger2 = new Passenger();
        passenger2.setFirstName("Mar");
        passenger2.setLastName("Camel");

        Ticket ticket2 = new Ticket();
        ticket2.setPrice(450d);
        ticket2.setPassenger(passenger2);

        Passenger passenger3 = new Passenger();
        passenger3.setFirstName("Forrest");
        passenger3.setLastName("Gump");

        Ticket ticket3 = new Ticket();
        ticket3.setPrice(999d);
        ticket3.setPassenger(passenger3);

        Calendar calendar = Calendar.getInstance();
        TimeZone zone = TimeZone.getTimeZone("America/Chicago");

        calendar.setTimeZone(zone);
        calendar.set(2021, Calendar.SEPTEMBER, 21, 14, 00);

        Date date = calendar.getTime();

        flight1.setDepartsOn(date);
        flight1.addTicket(ticket1);
        flight1.addTicket(ticket2);
        flight1.addTicket(ticket3);

        return flight1;
    }
}
