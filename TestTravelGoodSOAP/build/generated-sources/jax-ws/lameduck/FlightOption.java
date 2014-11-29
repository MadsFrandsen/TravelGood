
package lameduck;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for flightOption complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flightOption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="airlineReservationService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bookingNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="flight" type="{http://LameDuck/}flight" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flightOption", propOrder = {
    "airlineReservationService",
    "bookingNumber",
    "flight",
    "price"
})
public class FlightOption {

    protected String airlineReservationService;
    protected int bookingNumber;
    protected Flight flight;
    protected int price;

    /**
     * Gets the value of the airlineReservationService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAirlineReservationService() {
        return airlineReservationService;
    }

    /**
     * Sets the value of the airlineReservationService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAirlineReservationService(String value) {
        this.airlineReservationService = value;
    }

    /**
     * Gets the value of the bookingNumber property.
     * 
     */
    public int getBookingNumber() {
        return bookingNumber;
    }

    /**
     * Sets the value of the bookingNumber property.
     * 
     */
    public void setBookingNumber(int value) {
        this.bookingNumber = value;
    }

    /**
     * Gets the value of the flight property.
     * 
     * @return
     *     possible object is
     *     {@link Flight }
     *     
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Sets the value of the flight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Flight }
     *     
     */
    public void setFlight(Flight value) {
        this.flight = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(int value) {
        this.price = value;
    }

}
