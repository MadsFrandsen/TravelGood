
package ws.travelgood.xml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.travelgood.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.travelgood.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FlightBooking }
     * 
     */
    public FlightBooking createFlightBooking() {
        return new FlightBooking();
    }

    /**
     * Create an instance of {@link Stay }
     * 
     */
    public Stay createStay() {
        return new Stay();
    }

    /**
     * Create an instance of {@link Itinerary }
     * 
     */
    public Itinerary createItinerary() {
        return new Itinerary();
    }

    /**
     * Create an instance of {@link AccommodationOptions }
     * 
     */
    public AccommodationOptions createAccommodationOptions() {
        return new AccommodationOptions();
    }

}
