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
import javax.ws.rs.WebApplicationException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import lameduck.FlightOption;
import lameduck.LameDuckException_Exception;
import model.BookingItem;
import model.BookingItem.BookingStatus;
import model.Flight;
import model.Hotel;
import model.Itinerary;
import model.Location;
import model.Route;
import niceview.NiceViewFault_Exception;
import niceview.Reservation;
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
    public void cancelItineraryBooking(@PathParam("itineraryId") String itineraryId) {
        Itinerary itinerary = itineraries.get(itineraryId);
        
        boolean failing = false;
        for (Flight flight : itinerary.getFlights()) {
            if(flight.getBookingStatus() == BookingItem.BookingStatus.CONFIRMED) {
                try {    
                    boolean cancelled = cancelFlightLameDuck(Integer.parseInt(flight.getBookingId()), flight.getPrice(), itinerary.getCreditCard());
                    if(cancelled) {
                        flight.setBookingStatus(BookingStatus.CANCELLED);
                    } else {
                        failing = true;
                    }
                } catch (LameDuckException_Exception ex) {
                    failing = true;
                }
            }
        }

        for (Hotel hotel : itineraries.get(itineraryId).getHotels()) {
            if(hotel.getBookingStatus() == BookingItem.BookingStatus.CONFIRMED) {
                try {
                    cancelHotelNiceView(Integer.parseInt(hotel.getBookingId()));
                    hotel.setBookingStatus(BookingStatus.CANCELLED);
                } catch(NiceViewFault_Exception e) {
                    failing = true;
                }
            }
        }
        
        if(failing) {
            throw new CancelException();
        }
    }

    @Path("{itineraryId}/book")
    @POST
    public void bookItinerary(@PathParam("itineraryId") String itineraryId,
            @FormParam("creditCardNumber") String creditCardNumber,
            @FormParam("creditCardOwnerName") String creditCardOwnerName,
            @FormParam("creditCardExpMonth") int creditCardExpMonth,
            @FormParam("creditCardExpYear") int creditCardExpYear) {

        CreditCardInfoType cc = new CreditCardInfoType();
        cc.setName(creditCardOwnerName);
        cc.setNumber(creditCardNumber);
        ExpirationDateType expirationDate = new ExpirationDateType();
        expirationDate.setMonth(creditCardExpMonth);
        expirationDate.setYear(creditCardExpYear);
        cc.setExpirationDate(expirationDate);
        
        Itinerary itinerary = itineraries.get(itineraryId);
        itinerary.setCreditCard(cc);
        
        boolean failing = false;
        Exception failingException = null;
        for (Flight flight : itinerary.getFlights()) {
            int bookingNumber = Integer.parseInt(flight.getBookingId());
            try {
                boolean booked = bookFlightLameDuck(bookingNumber, cc);
                if(booked) {
                flight.setBookingStatus(BookingStatus.CONFIRMED);
                } else {
                    failing = true;
                }
            } catch (LameDuckException_Exception ex) {
                failing = true;
                failingException = ex;
                break;
            }
        }

        if(!failing) {
            for (Hotel hotel : itineraries.get(itineraryId).getHotels()) {
                int bookingNumber = Integer.parseInt(hotel.getBookingId());
                try {
                boolean booked = bookHotelNiceView(bookingNumber, cc);
                if(booked) {
                    hotel.setBookingStatus(BookingStatus.CONFIRMED);
                } else {
                    failing = true;
                }
            } catch (NiceViewFault_Exception ex) {
                failing = true;
                failingException = ex;
                break;
            }
            }
        }
        
        if(failing) {
            try {
                cancelItineraryBooking(itineraryId); 
            } catch (CancelException ex) {
                // Cancelling failed, a booking exception will already be thrown.
            }
            throw new BookingException();
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
    public Hotel[] getHotels(@QueryParam("departureDate") String departureDate,
            @QueryParam("arrivalDate") String arrivalDate,
            @QueryParam("location") Location location) throws ParseException, DatatypeConfigurationException {
        GregorianCalendar departure = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        departure.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(departureDate));
        departure.add(Calendar.DATE, 1);
        
        GregorianCalendar arrival = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
        arrival.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(arrivalDate));
        arrival.add(Calendar.DATE, 1);
        
        List<Reservation> reservations = getHotelsNiceView(location.getName(), 
                DatatypeFactory.newInstance().newXMLGregorianCalendar(arrival),
                DatatypeFactory.newInstance().newXMLGregorianCalendar(departure));
        Hotel[] hotels = new Hotel[reservations.size()];
        for (int i = 0; i < hotels.length; i++) {
            Reservation reservation = reservations.get(i);
            hotels[i] = new Hotel(String.valueOf(reservation.getBookingNumber()),
                    new Location(reservation.getAddress()),
                    arrival,
                    departure);
        }
        return hotels;
    }

    @Path("{itineraryId}/hotels/{hotelId}")
    @PUT
    public void addHotel(@PathParam("hotelId") String hotelId, @PathParam("itineraryId") String itineraryId) {
        itineraries.get(itineraryId).getHotels().add(new Hotel(hotelId));
    }

    public class BookingException extends WebApplicationException {
    }

    public class CancelException extends WebApplicationException {
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

    private static boolean bookHotelNiceView(int bookingNumber, dk.dtu.imm.fastmoney.types.CreditCardInfoType creditCardInfo) throws NiceViewFault_Exception {
        niceview.NiceViewService service = new niceview.NiceViewService();
        niceview.NiceViewWebService port = service.getNiceViewWebServicePort();
        return port.bookHotel(bookingNumber, creditCardInfo);
    }

    private static void cancelHotelNiceView(int bookingNumber) throws NiceViewFault_Exception {
        niceview.NiceViewService service = new niceview.NiceViewService();
        niceview.NiceViewWebService port = service.getNiceViewWebServicePort();
        port.cancelHotel(bookingNumber);
    }

    private static java.util.List<niceview.Reservation> getHotelsNiceView(java.lang.String city, javax.xml.datatype.XMLGregorianCalendar arrival, javax.xml.datatype.XMLGregorianCalendar departure) {
        niceview.NiceViewService service = new niceview.NiceViewService();
        niceview.NiceViewWebService port = service.getNiceViewWebServicePort();
        return port.getHotels(city, arrival, departure);
    }
}
