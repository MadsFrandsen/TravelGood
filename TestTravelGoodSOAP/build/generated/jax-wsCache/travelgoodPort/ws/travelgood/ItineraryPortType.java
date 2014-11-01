
package ws.travelgood;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.travelgood.xml.AccommodationOptions;
import ws.travelgood.xml.Itinerary;
import ws.travelgood.xml.ObjectFactory;
import ws.travelgood.xml.TravelOptions;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "itineraryPortType", targetNamespace = "http://travelgood.ws")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ItineraryPortType {


    /**
     * 
     * @param personName
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "itineraryID", partName = "itineraryID")
    public String createItinerary(
        @WebParam(name = "personName", partName = "personName")
        String personName);

    /**
     * 
     * @param cardOwner
     * @param creditCardNumber
     * @param itineraryID
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(name = "successful", partName = "successful")
    public boolean bookItinerary(
        @WebParam(name = "itineraryID", partName = "itineraryID")
        String itineraryID,
        @WebParam(name = "creditCardNumber", partName = "creditCardNumber")
        int creditCardNumber,
        @WebParam(name = "cardOwner", partName = "cardOwner")
        String cardOwner);

    /**
     * 
     * @param bookingNumber
     * @param itineraryID
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(name = "successfull", partName = "successfull")
    public boolean addFlight(
        @WebParam(name = "itineraryID", partName = "itineraryID")
        String itineraryID,
        @WebParam(name = "bookingNumber", partName = "bookingNumber")
        int bookingNumber);

    /**
     * 
     * @param bookingNumber
     * @param itineraryID
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(name = "successfull", partName = "successfull")
    public boolean addHotel(
        @WebParam(name = "itineraryID", partName = "itineraryID")
        String itineraryID,
        @WebParam(name = "bookingNumber", partName = "bookingNumber")
        int bookingNumber);

    /**
     * 
     * @param itineraryID
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(name = "success", partName = "success")
    public boolean cancelItinerary(
        @WebParam(name = "itineraryID", partName = "itineraryID")
        String itineraryID);

    /**
     * 
     * @param departureDestination
     * @param time
     * @param arrivalDestination
     * @return
     *     returns ws.travelgood.xml.TravelOptions
     */
    @WebMethod
    @WebResult(name = "flightList", partName = "flightList")
    public TravelOptions getFlights(
        @WebParam(name = "arrivalDestination", partName = "arrivalDestination")
        String arrivalDestination,
        @WebParam(name = "time", partName = "time")
        XMLGregorianCalendar time,
        @WebParam(name = "departureDestination", partName = "departureDestination")
        String departureDestination);

    /**
     * 
     * @param start
     * @param end
     * @param city
     * @return
     *     returns ws.travelgood.xml.AccommodationOptions
     */
    @WebMethod
    @WebResult(name = "hotels", partName = "hotels")
    public AccommodationOptions getHotels(
        @WebParam(name = "start", partName = "start")
        XMLGregorianCalendar start,
        @WebParam(name = "end", partName = "end")
        XMLGregorianCalendar end,
        @WebParam(name = "city", partName = "city")
        String city);

    /**
     * 
     * @param id
     * @return
     *     returns ws.travelgood.xml.Itinerary
     */
    @WebMethod
    @WebResult(name = "itinerary", partName = "itinerary")
    public Itinerary getItinerary(
        @WebParam(name = "id", partName = "id")
        String id);

}
