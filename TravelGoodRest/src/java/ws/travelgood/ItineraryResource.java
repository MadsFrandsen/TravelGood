/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.travelgood;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
    public String createItinerary() {
        Itinerary itinerary = new Itinerary(UUID.randomUUID().toString());
        itineraries.put(itinerary.getId(), itinerary);
        return itinerary.getId();
    }
 
    @Path("{id}")
    @DELETE
    public void cancelItinerary(String id) throws CancelException, UnknownItineraryException {
        Itinerary itinerary = itineraries.get(id);
        
        if(itinerary == null) {
            throw new UnknownItineraryException(id);
        }
        
        List<String> failedFlightIds = new ArrayList<String>();
        for(Flight flight : itinerary.getFlights()) {
            try {
                // TODO: Cancel flight
            } catch(Exception e) {
                failedFlightIds.add(flight.getBookingId());
            }
        }
        
        List<String> failedHotelIds = new ArrayList<String>();
        for(Hotel hotel : itinerary.getHotels()) {
            try {
                // TODO: Cancel hotel
            } catch(Exception e) {
                failedHotelIds.add(hotel.getBookingId());
            }
        }
        
        if(!failedFlightIds.isEmpty() || !failedHotelIds.isEmpty()) {
            throw new CancelException(failedFlightIds, failedHotelIds);
        }
    }
    
    @Path("{itineraryId}/book")
    @POST
    public void bookItinerary(@PathParam("itineraryId") String itineraryId) throws BookingException, UnknownItineraryException, CancelException {
        Itinerary itinerary = itineraries.get(itineraryId);
        
        if(itinerary == null) {
            throw new UnknownItineraryException(itineraryId);
        }
        
        try {   
            for(Flight flight : itinerary.getFlights()) {
                // TODO: Book flight
            }

            for(Hotel hotel : itinerary.getHotels()) {
                // TODO: Book hotel
            }
        } catch(Exception e) {
            // If any booking fails, we cancel all bookings
            cancelItinerary(itineraryId);
        }
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
    
    public class BookingException extends Exception {
        
    }
    
    public class CancelException extends Exception {
        public CancelException(List<String> flightIds, List<String> hotelIds) {
            super("Cancelling some flights/hotels failed" + getCancelString(flightIds, hotelIds));
        }
    }
    
    private static String getCancelString(List<String> flightIds, List<String> hotelIds) {
        String flightString = "";
        if(!flightIds.isEmpty()) {
            flightString += ". The following flight ids are still booked: " + flightIds.get(0);
            for(int i = 1; i < flightIds.size(); i++) {
                flightString += ", " + flightIds.get(1);
            }
        }
        
        String hotelsString = "";
        if(!hotelIds.isEmpty()) {
            hotelsString += ". The following hotel ids are still booked: " + hotelIds.get(0);
            for(int i = 1; i < hotelIds.size(); i++) {
                hotelsString += ", " + hotelIds.get(1);
            }
        }
        
        return flightString + hotelsString;
    }
    
    public class UnknownItineraryException extends Exception {
        public UnknownItineraryException(String itineraryId) {
            super("No itinerary found with the id: " + itineraryId);
        }
    }
}
