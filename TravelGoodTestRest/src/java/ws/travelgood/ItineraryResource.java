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
    
    private static Map<String, Itinerary> itineraries;
    
    @Path("{id}")
    @GET
    public String getItinerary(@PathParam("id") String id) {
        return id;
        //return itineraries.get(id);
    }
    
    @POST
    public int createItinerary() {
        throw new NotImplementedException();
    }
 
    @Path("booking")
    @DELETE
    public void cancelItineraryBooking(String id) {
        throw new NotImplementedException();
    }
    
    @Path("booking")
    @POST
    public void bookItinerary(String id) {
        throw new NotImplementedException();
    }
    
    @Path("flights")
    @GET
    public List<Flight> getFlights(@QueryParam("departureLocation") Location departureLocation,
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
    public List<Hotel> getHotels(@QueryParam("departureDate") String departureDate, 
        @QueryParam("arrivalDate") String arrivalDate, 
        @QueryParam("location") Location location) throws ParseException {
        Date departure = new SimpleDateFormat().parse(departureDate);
        Date arrival = new SimpleDateFormat().parse(arrivalDate);
        throw new NotImplementedException();
    }
    
    @Path("hotels")
    @PUT
    public void addHotel(String hotelId, @PathParam("itineraryId") String itineraryId) {
        throw new NotImplementedException();
    }
}
