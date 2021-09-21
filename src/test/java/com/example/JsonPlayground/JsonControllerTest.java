package com.example.JsonPlayground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(JsonController.class)
public class JsonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testFlight() throws Exception {
        this.mvc.perform(
                        get("/flights/flight")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departsOn", is("2021-09-21@14:00")))
                .andExpect(jsonPath("$.tickets[0].passenger.FirstName", is("John")));
    }
    @Test
    public void testFlights() throws Exception {
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tickets[0].passenger.FirstName", is("John")));
    }

    @Test
    public void testFlightsCreatesTwoFlights() throws Exception {
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].tickets[0].passenger.FirstName", is("Mar")));
    }

    @Test
    void testTicketHasPrice() throws Exception {
        this.mvc.perform(
                        get("/flights")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].tickets[0].price", is(450.0)));
    }

    @Test
    public void testSendsJsonAsStringLiteral() throws Exception {

        String testString = "{\"tickets\":[{\"passenger\":{\"firstName\":\"Marshall\"," +
                "\"lastName\":\"Mathers\"},\"price\":200},{\"passenger\":{\"firstName\":\"Mark\"," +
                "\"lastName\":\"Hamill\"},\"price\":150}]}";


        this.mvc.perform(post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testString))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350.0}"));
    }

    @Test
    public void testSendsJsonAsJacksonSerialized() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        Flight testFlight = new Flight();
        Ticket testTicket = new Ticket();
        Passenger testPassenger = new Passenger();

        testPassenger.setFirstName("Bob");
        testPassenger.setLastName("Smith");
        testTicket.setPassenger(testPassenger);
        testTicket.setPrice(500.0);
        testFlight.addTicket(testTicket);

        String body = mapper.writeValueAsString(testFlight);


        this.mvc.perform(post("/flights/tickets/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":500.0}"));
    }

    @Test
    public void testSendsJsonFromFile() throws Exception {
        String json = getJSON("/data.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":800.0}"));
    }

        private String getJSON(String path) throws Exception {
            URL url = this.getClass().getResource(path);
            return new String(Files.readAllBytes(Paths.get(url.getFile())));

    }

    @Test
    public void testSendsJsonAsJacksonSerializedMap() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, List<Ticket>> flightMap = new HashMap<>();
        List<Ticket> ticketList = new ArrayList<>();

        Ticket testTicket = new Ticket();
        Passenger testPassenger = new Passenger();

        testPassenger.setFirstName("Bob");
        testPassenger.setLastName("Smith");
        testTicket.setPassenger(testPassenger);
        testTicket.setPrice(500.0);

        ticketList.add(testTicket);
        flightMap.put("tickets", ticketList);

        String body = mapper.writeValueAsString(flightMap);


        this.mvc.perform(post("/flights/tickets/total")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":500.0}"));
    }
}