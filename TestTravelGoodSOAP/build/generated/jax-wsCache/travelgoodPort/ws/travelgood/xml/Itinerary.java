
package ws.travelgood.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for itinerary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itinerary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hotelbookings" type="{http://xml.travelgood.ws}stay" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://xml.travelgood.ws}personName"/>
 *         &lt;element name="status" type="{http://xml.travelgood.ws}bookingStatus"/>
 *         &lt;element name="bookingDate" type="{http://xml.travelgood.ws}bookingDate" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://xml.travelgood.ws}itineraryID"/>
 *         &lt;element name="flightBookings" type="{http://xml.travelgood.ws}flightBooking" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itinerary", propOrder = {
    "hotelbookings",
    "owner",
    "status",
    "bookingDate",
    "id",
    "flightBookings"
})
public class Itinerary {

    protected List<Stay> hotelbookings;
    @XmlElement(required = true)
    protected String owner;
    @XmlElement(required = true)
    protected String status;
    protected XMLGregorianCalendar bookingDate;
    @XmlElement(name = "ID", required = true)
    protected String id;
    protected List<FlightBooking> flightBookings;

    /**
     * Gets the value of the hotelbookings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hotelbookings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHotelbookings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Stay }
     * 
     * 
     */
    public List<Stay> getHotelbookings() {
        if (hotelbookings == null) {
            hotelbookings = new ArrayList<Stay>();
        }
        return this.hotelbookings;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the bookingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the value of the bookingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBookingDate(XMLGregorianCalendar value) {
        this.bookingDate = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the flightBookings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightBookings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightBookings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightBooking }
     * 
     * 
     */
    public List<FlightBooking> getFlightBookings() {
        if (flightBookings == null) {
            flightBookings = new ArrayList<FlightBooking>();
        }
        return this.flightBookings;
    }

}
