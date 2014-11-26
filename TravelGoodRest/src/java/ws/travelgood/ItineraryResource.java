/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.travelgood;

import dk.dtu.imm.fastmoney.types.CreditCardInfoType;
import dk.dtu.imm.fastmoney.types.ExpirationDateType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import lameduck.FlightOption;
import lameduck.LameDuckException_Exception;
import model.BookingItem;
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
    @Produces({"application/xml"})
    public Itinerary getItinerary(@PathParam("id") String id) {
        return itineraries.get(id);
    }

    @POST
    public String createItinerary() {
        String id = UUID.randomUUID().toString();
        itineraries.put(id, new Itinerary(id));
        return id;
    }

    @Path("{itineraryId}")
    @DELETE
    public void cancelItineraryBooking(@PathParam("itineraryId") String itineraryId,
            String creditCardNumber,
            String creditCardOwnerName,
            int creditCardExpMonth,
            int creditCardExpYear) throws CancelException {
        
        CreditCardInfoType cc = new CreditCardInfoType();
        cc.setName(creditCardOwnerName);
        cc.setNumber(creditCardNumber);
        ExpirationDateType expirationDate = new ExpirationDateType();
        expirationDate.setMonth(creditCardExpMonth);
        expirationDate.setYear(creditCardExpYear);
        cc.setExpirationDate(expirationDate);
        
        boolean failing = false;
        for (Flight flight : itineraries.get(itineraryId).getFlights()) {
            if(flight.getBookingStatus() == BookingItem.BookingStatus.CONFIRMED) {
                try {    
                    cancelFlightLameDuck(Integer.parseInt(flight.getBookingId()), flight.getPrice(), cc);
                } catch (LameDuckException_Exception ex) {
                    failing = true;
                }
            }
        }

        for (Hotel hotel : itineraries.get(itineraryId).getHotels()) {
            // CANCEL HOTEL
        }
        
        if(failing) {
            throw new CancelException();
        }
    }

    @Path("{itineraryId}/book")
    @POST
    public void bookItinerary(@PathParam("itineraryId") String itineraryId,
            String creditCardNumber,
            String creditCardOwnerName,
            int creditCardExpMonth,
            int creditCardExpYear) throws BookingException {

        CreditCardInfoType cc = new CreditCardInfoType();
        cc.setName(creditCardOwnerName);
        cc.setNumber(creditCardNumber);
        ExpirationDateType expirationDate = new ExpirationDateType();
        expirationDate.setMonth(creditCardExpMonth);
        expirationDate.setYear(creditCardExpYear);
        cc.setExpirationDate(expirationDate);
        
        boolean failing = false;
        Exception failingException = null;
        for (Flight flight : itineraries.get(itineraryId).getFlights()) {
            try {
                bookFlightLameDuck(Integer.parseInt(flight.getBookingId()), cc);
            } catch (LameDuckException_Exception ex) {
                failing = true;
                failingException = ex;
                break;
            }
        }

        if(!failing) {
            for (Hotel hotel : itineraries.get(itineraryId).getHotels()) {
                // BOOK HOTEL
            }
        }
        
        if(failing) {
            try {
                cancelItineraryBooking(itineraryId, cc.getNumber(), 
                        cc.getName(), 
                        cc.getExpirationDate().getMonth(), 
                        cc.getExpirationDate().getYear()); 
            } catch (CancelException ex) {
                // Cancelling failed, a booking exception will already be thrown.
            }
            throw (BookingException) new BookingException().initCause(failingException);
        }
    }

    @Path("flights")
    @GET
    @Produces({"application/xml"})
    public Flight[] getFlights(@QueryParam("departureLocation") Location departureLocation,
            @QueryParam("arrivalLocation") Location arrivalLocation,
            @QueryParam("departureDate") String departureDate) throws ParseException, LameDuckException_Exception, DatatypeConfigurationException {
        GregorianCalendar departure = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        departure.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(departureDate));
        departure.add(Calendar.DATE, 1);
        List<lameduck.FlightOption> flightOptions = getFlightsLameDuck(departureLocation.getName(),
                arrivalLocation.getName(),
                DatatypeFactory.newInstance().newXMLGregorianCalendar(departure));
        Flight[] flights = new Flight[flightOptions.size()];
        for (int i = 0; i < flights.length; i++) {
            FlightOption option = flightOptions.get(i);
            flights[i] = new Flight(String.valueOf(option.getBookingNumber()),
                    new Route(
                    new Location(option.getFlight().getSource()),
                    new Location(option.getFlight().getDestination())),
                    option.getFlight().getArrivalTime().toGregorianCalendar(),
                    option.getPrice());
        }
        return flights;
    }

    @Path("{itineraryId}/flights/{flightId}")
    @PUT
    public void addFlight(@PathParam("flightId") String flightId, @PathParam("itineraryId") String itineraryId, @FormParam("price") String price) {
        itineraries.get(itineraryId).getFlights().add(new Flight(flightId, Integer.parseInt(price)));
    }

    @Path("hotels")
    @GET
    @Produces({"application/xml"})
    public Flight[] getHotels(@QueryParam("departureDate") String departureDate,
            @QueryParam("arrivalDate") String arrivalDate,
            @QueryParam("location") Location location) throws ParseException {
        Date departure = new SimpleDateFormat().parse(departureDate);
        Date arrival = new SimpleDateFormat().parse(arrivalDate);
        throw new NotImplementedException();
    }

    @Path("{itineraryId}/hotels/{hotelId}")
    @PUT
    public void addHotel(String hotelId, @PathParam("itineraryId") String itineraryId) {
        throw new NotImplementedException();
    }

    public class BookingException extends Exception {
    }

    public class CancelException extends Exception {
    }

    private static boolean bookFlightLameDuck(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard) throws LameDuckException_Exception {
        lameduck.LameDuckWebService_Service service = new lameduck.LameDuckWebService_Service();
        lameduck.LameDuckWebService port = service.getLameDuckWebServicePort();
        return port.bookFlight(bookingNumber, creditCard);
    }

    private static boolean cancelFlightLameDuck(int bookingNumber, int price, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCard) throws LameDuckException_Exception {
        lameduck.LameDuckWebService_Service service = new lameduck.LameDuckWebService_Service();
        lameduck.LameDuckWebService port = service.getLameDuckWebServicePort();
        return port.cancelFlight(bookingNumber, price, creditCard);
    }

    private static java.util.List<lameduck.FlightOption> getFlightsLameDuck(java.lang.String from, java.lang.String to, javax.xml.datatype.XMLGregorianCalendar date) throws LameDuckException_Exception {
        lameduck.LameDuckWebService_Service service = new lameduck.LameDuckWebService_Service();
        lameduck.LameDuckWebService port = service.getLameDuckWebServicePort();
        return port.getFlights(from, to, date);
    }
}
