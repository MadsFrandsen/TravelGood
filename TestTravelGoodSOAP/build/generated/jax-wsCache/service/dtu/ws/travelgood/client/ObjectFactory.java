
package dtu.ws.travelgood.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dtu.ws.travelgood.client package. 
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

    private final static QName _FlightInformation_QNAME = new QName("http://xml.netbeans.org/schema/travelGoodBEPL", "flightInformation");
    private final static QName _Flight_QNAME = new QName("http://xml.netbeans.org/schema/travelGoodBEPL", "flight");
    private final static QName _CreditCardNumber_QNAME = new QName("http://xml.netbeans.org/schema/travelGoodBEPL", "creditCardNumber");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dtu.ws.travelgood.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FlightType }
     * 
     */
    public FlightType createFlightType() {
        return new FlightType();
    }

    /**
     * Create an instance of {@link FlightInformationType }
     * 
     */
    public FlightInformationType createFlightInformationType() {
        return new FlightInformationType();
    }

    /**
     * Create an instance of {@link HotelInformationType }
     * 
     */
    public HotelInformationType createHotelInformationType() {
        return new HotelInformationType();
    }

    /**
     * Create an instance of {@link HotelList }
     * 
     */
    public HotelList createHotelList() {
        return new HotelList();
    }

    /**
     * Create an instance of {@link ItineraryType }
     * 
     */
    public ItineraryType createItineraryType() {
        return new ItineraryType();
    }

    /**
     * Create an instance of {@link FlightList }
     * 
     */
    public FlightList createFlightList() {
        return new FlightList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightInformationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/travelGoodBEPL", name = "flightInformation")
    public JAXBElement<FlightInformationType> createFlightInformation(FlightInformationType value) {
        return new JAXBElement<FlightInformationType>(_FlightInformation_QNAME, FlightInformationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlightType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/travelGoodBEPL", name = "flight")
    public JAXBElement<FlightType> createFlight(FlightType value) {
        return new JAXBElement<FlightType>(_Flight_QNAME, FlightType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.netbeans.org/schema/travelGoodBEPL", name = "creditCardNumber")
    public JAXBElement<Integer> createCreditCardNumber(Integer value) {
        return new JAXBElement<Integer>(_CreditCardNumber_QNAME, Integer.class, null, value);
    }

}
