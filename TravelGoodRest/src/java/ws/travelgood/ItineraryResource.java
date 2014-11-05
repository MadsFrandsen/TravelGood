/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.travelgood;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import model.Flight;
import model.Hotel;
import model.Itinerary;
import model.Location;
import model.Route;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Mads
 */
@Path("itineraries")
public class ItineraryResource {
    
    private static Map<String, Itinerary> itineraries = new HashMap<String, Itinerary>();
    
    @Path("{id}")
    @GET
    public Itinerary getItinerary(@PathParam("id") String id) {
        return itineraries.get(id);
    }
    
    @POST
    public int createItinerary() {
        throw new NotImplementedException();
    }
 
    @Path("{id}")
    @DELETE
    public void cancelItineraryBooking(String id) {
        throw new NotImplementedException();
    }
    
    @Path("{itineraryId}/book")
    @POST
    public void bookItinerary(@PathParam("itineraryId") String itineraryId) {
        throw new NotImplementedException();
    }
    
    @Path("flights")
    @GET
    public Flight[] getFlights(@QueryParam("departureLocation") Location departureLocation,
        @QueryParam("arrivalLocation") Location arrivalLocation,
        @QueryParam("departureDate") String departureDate) throws ParseException {
        Date departure = new SimpleDateFormat().parse(departureDate);
        throw new NotImplementedException();
    }
    
    @Path("{itineraryId}/flights")
    @PUT
    public void addFlight(String flightId, @PathParam("itineraryId") String itineraryId) {
        throw new NotImplementedException();
    }
    
    @Path("hotels")
    @GET
    public Flight[] getHotels(@QueryParam("departureDate") String departureDate, 
        @QueryParam("arrivalDate") String arrivalDate, 
        @QueryParam("location") Location location) throws ParseException {
        Date departure = new SimpleDateFormat().parse(departureDate);
        Date arrival = new SimpleDateFormat().parse(arrivalDate);
        throw new NotImplementedException();
    }
    
    @Path("{itineraryId}/hotels")
    @PUT
    public void addHotel(String hotelId, @PathParam("itineraryId") String itineraryId) {
        throw new NotImplementedException();
    }
}
