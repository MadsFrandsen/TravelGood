
package dtu.ws.travelgood.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for itineraryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="itineraryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://xml.netbeans.org/schema/travelGoodBEPL}flightInformation"/>
 *           &lt;element name="newElement" type="{http://xml.netbeans.org/schema/travelGoodBEPL}hotelInformationType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itineraryType", namespace = "http://travelgood.ws", propOrder = {
    "flightInformation",
    "newElement"
})
public class ItineraryType {

    @XmlElement(namespace = "http://xml.netbeans.org/schema/travelGoodBEPL")
    protected FlightInformationType flightInformation;
    @XmlElement(namespace = "")
    protected HotelInformationType newElement;

    /**
     * Gets the value of the flightInformation property.
     * 
     * @return
     *     possible object is
     *     {@link FlightInformationType }
     *     
     */
    public FlightInformationType getFlightInformation() {
        return flightInformation;
    }

    /**
     * Sets the value of the flightInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightInformationType }
     *     
     */
    public void setFlightInformation(FlightInformationType value) {
        this.flightInformation = value;
    }

    /**
     * Gets the value of the newElement property.
     * 
     * @return
     *     possible object is
     *     {@link HotelInformationType }
     *     
     */
    public HotelInformationType getNewElement() {
        return newElement;
    }

    /**
     * Sets the value of the newElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotelInformationType }
     *     
     */
    public void setNewElement(HotelInformationType value) {
        this.newElement = value;
    }

}
