 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.util.Random;
import model.Flight;
import model.Hotel;
import model.Itinerary;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mads
 */
public class TestItineraryResource {
    
    public TestItineraryResource() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testPlanningAndBooking() {
        Client client = Client.create();
        
        // Create itinerary
        WebResource resourceCreateItinerary = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/");
        String itineraryId = resourceCreateItinerary.post(String.class);
        
        addFlightToItinerary(client, itineraryId, "Copenhagen", "London", "27-12-2014");
        addHotelToItinerary(client, itineraryId, "London", "27-12-2014", "29-12-2014");
        addFlightToItinerary(client, itineraryId, "London", "Paris", "29-12-2014");
        addFlightToItinerary(client, itineraryId, "Paris", "Copenhagen", "3-1-2015");
        addHotelToItinerary(client, itineraryId, "Copenhagen", "3-1-2015", "8-1-2015");
        
        // Retrieve
        WebResource resourceGetItinerary = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/" + 
                "?id=" + itineraryId);
        Itinerary itinerary = resourceCreateItinerary.get(Itinerary.class);
        
        // TODO: Add assertions
    }
    
    private void addFlightToItinerary(Client client, String itineraryId, String origin, String destination, String departureDate) {
        // Get flights
        WebResource resourceFlights = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/flights/" +
                "?departureLocation=" + origin + 
                "&arrivalLocation=" + destination + 
                "&departureDate=" + departureDate);
        Flight[] flights = resourceFlights.get(Flight[].class);
        
        // Randomly picks a flight from the list, and adds it to the itinerary
        WebResource addFlightResource = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/" +
                itineraryId +
                "/flights");
        int randomIndex = new Random().nextInt(flights.length);
        addFlightResource.put(flights[randomIndex].getBookingId());
    }
    
    private void addHotelToItinerary(Client client, String itineraryId, String location, String arrivalDate, String departureDate) {
        // Get hotels
        WebResource resourceFlights = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/hotels/" +
                "?location=" + location + 
                "&arrivalDate=" + arrivalDate + 
                "&departureDate=" + departureDate);
        Hotel[] hotels = resourceFlights.get(Hotel[].class);
        
        // Randomly picks a hotel from the list, and adds it to the itinerary
        WebResource addHotelResource = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/" +
                itineraryId + 
                "/hotels");
        int randomIndex = new Random().nextInt(hotels.length);
        addHotelResource.put(hotels[randomIndex].getBookingId());
    }
    
    @Test
    public void testCancelPlanning() {
        
    }
    
    @Test
    public void testBookingFails() {
        
    }
    
    @Test
    public void testCancelBooking() {
        
    }
    
    public void testCancelFails() {
        
    }
}