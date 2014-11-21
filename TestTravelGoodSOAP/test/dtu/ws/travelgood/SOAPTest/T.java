package dtu.ws.travelgood.SOAPTest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import lameduck.FlightOption;
import lameduck.GetFlightsResponse;
import ws.travelgood.xml.AccommodationOptions;
import ws.travelgood.xml.FlightBooking;
import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.Stay;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jonas
 */
public class T {

    public DatatypeFactory df;
    public String[] personNames, destinations, unbookableDestinations;
    public XMLGregorianCalendar[] dates;
    public int[] creditCardNumbers;
    public FlightOption[] travels;
    public FlightBooking[] itineraryTravels;
    public Stay[] stays, itinearyStays;
    public String itinearyID;
    public Itinerary itineary; 

    public T() throws DatatypeConfigurationException {
        df = DatatypeFactory.newInstance();
        personNames = new String[3];
        personNames[0] = "Poul Thomsen";
        personNames[1] = "Søren Frederiksen";
        personNames[2] = "Peter Pan";

        destinations = new String[3];
        destinations[0] = "Copenhagen";
        destinations[1] = "Berlin";
        destinations[2] = "Oslo";
        
        unbookableDestinations = new String[3];
        unbookableDestinations[0] = "Kiev";
        unbookableDestinations[1] = "Hamburg";
        
        dates = new XMLGregorianCalendar[4];
        dates[0] = df.newXMLGregorianCalendar("2015-09-15");
        dates[1] = df.newXMLGregorianCalendar("2015-09-16");
        dates[2] = df.newXMLGregorianCalendar("2015-09-17");
        dates[3] = df.newXMLGregorianCalendar("2015-09-18");

        creditCardNumbers = new int[2];
        creditCardNumbers[0] = 1121222224;
        creditCardNumbers[1] = 1121222224;

    }

    public boolean addFlight(java.lang.String itineraryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addFlight(itineraryID, bookingNumber);
    }

    public boolean addHotel(java.lang.String itineraryID, int bookingNumber) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.addHotel(itineraryID, bookingNumber);
    }

    public boolean bookItinerary(java.lang.String itineraryID, int creditCardNumber, java.lang.String cardOwner) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.bookItinerary(itineraryID, creditCardNumber, cardOwner);
    }

    public boolean cancelItinerary(java.lang.String itineraryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.cancelItinerary(itineraryID);
    }

    public String createItinerary(java.lang.String personName) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.createItinerary(personName);
    }

    public Itinerary getItinerary(java.lang.String itineraryID) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getItinerary(itineraryID);
    }

    public GetFlightsResponse getFlights(java.lang.String itineraryID, java.lang.String arrivalDestination, javax.xml.datatype.XMLGregorianCalendar time, java.lang.String departureDestination) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getFlights(itineraryID, arrivalDestination, time, departureDestination);
    }

    public AccommodationOptions getHotels(java.lang.String itineraryID, javax.xml.datatype.XMLGregorianCalendar start, javax.xml.datatype.XMLGregorianCalendar end, java.lang.String city) {
        ws.travelgood.TravelgoodService service = new ws.travelgood.TravelgoodService();
        ws.travelgood.ItineraryPortType port = service.getItineraryPortTypeBindingPort();
        return port.getHotels(itineraryID, start, end, city);
    }
}
