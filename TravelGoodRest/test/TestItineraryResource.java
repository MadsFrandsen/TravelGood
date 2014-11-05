 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void testP1() {
        Client client = Client.create();

        String itineraryId = createItinerary();

        addRandomFlightToItinerary(client, itineraryId, "Copenhagen", "London", "27-12-2014");
        addRandomHotelToItinerary(client, itineraryId, "London", "27-12-2014", "29-12-2014");
        addRandomFlightToItinerary(client, itineraryId, "London", "Paris", "29-12-2014");
        addRandomFlightToItinerary(client, itineraryId, "Paris", "Copenhagen", "3-1-2015");
        addRandomHotelToItinerary(client, itineraryId, "Copenhagen", "3-1-2015", "8-1-2015");

        Itinerary itinerary = getItinerary(client, itineraryId);

        // TODO: Add assertions
    }

    @Test
    public void testP2() {
        Client client = Client.create();

        String itineraryId = createItinerary();

        addRandomFlightToItinerary(client, itineraryId, "Copenhagen", "London", "27-12-2014");

        cancelItinerary(client, itineraryId);

        // TODO: Add assertions
    }

    @Test
    public void testB() {
    }

    @Test
    public void testC1() {
    }

    @Test
    public void testC2() {
    }
    
    private void cancelItinerary(Client client, String itineraryId) {
        WebResource resourceCancelItinerary = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/" +
                itineraryId);
        resourceCancelItinerary.delete();
    }
    
    private String createItinerary() {
        WebResource resourceCreateItinerary = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/");
        return resourceCreateItinerary.post(String.class);
    }
    
    private Itinerary getItinerary(Client client, String itineraryId) {
        WebResource resourceGetItinerary = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/"
                + itineraryId);
        return resourceGetItinerary.get(Itinerary.class);
    }

    private void addRandomFlightToItinerary(Client client, String itineraryId, String origin, String destination, String departureDate) {
        Flight[] flights = getFlights(client, origin, destination, departureDate);
        
        // Randomly picks a flight from the array, and adds it to the itinerary
        int randomIndex = new Random().nextInt(flights.length);
        addFlightToItinerary(client, itineraryId, flights[randomIndex]);
    }
    
    private Flight[] getFlights(Client client, String origin, String destination, String departureDate) {
        WebResource resourceFlights = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/flights/"
                + "?departureLocation=" + origin
                + "&arrivalLocation=" + destination
                + "&departureDate=" + departureDate);
        return resourceFlights.get(Flight[].class);
    }
    
    private void addFlightToItinerary(Client client, String itineraryId, Flight flight) {
        WebResource addFlightResource = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/"
                + itineraryId
                + "/flights");
        addFlightResource.put(flight.getBookingId());
    }

    private void addRandomHotelToItinerary(Client client, String itineraryId, String location, String arrivalDate, String departureDate) {
        Hotel[] hotels = getHotels(client, itineraryId, location, arrivalDate, departureDate);

        // Randomly picks a hotel from the array, and adds it to the itinerary
        int randomIndex = new Random().nextInt(hotels.length);
        addHotelToItinerary(client, itineraryId, hotels[randomIndex]);
    }
    
    private Hotel[] getHotels(Client client, String itineraryId, String location, String arrivalDate, String departureDate) {
        WebResource resourceFlights = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/hotels/"
                + "?location=" + location
                + "&arrivalDate=" + arrivalDate
                + "&departureDate=" + departureDate);
        return resourceFlights.get(Hotel[].class);
    }
    
    private void addHotelToItinerary(Client client, String itineraryId, Hotel hotel) {
        WebResource addFlightResource = client.resource("http://localhost:8080/TravelGoodTestRest/webresources/itineraries/"
                + itineraryId
                + "/hotels");
        addFlightResource.put(hotel.getBookingId());
    }
}