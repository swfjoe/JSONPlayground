package com.example.JSONplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(JSONController.class)
public class JSONControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testFlight() throws Exception {
        this.mvc.perform(
                        get("/flights/flight")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2021-09-21@14:00")))
                .andExpect(jsonPath("$.Tickets[0].Passenger.FirstName", is("John")));
    }
    @Test
    public void testFlights() throws Exception {
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.FirstName", is("John")));
    }

    @Test
    public void testFlightsCreatesTwoFlights() throws Exception {
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.FirstName", is("Mar")));
    }

    @Test
    void testTicketHasPrice() throws Exception {
        this.mvc.perform(
        get("/flights")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(450.0)));
    }
}