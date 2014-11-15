
package dtu.ws.travelgood.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element ref="{http://xml.netbeans.org/schema/travelGoodBEPL}flightInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hotelinformation" type="{http://xml.netbeans.org/schema/travelGoodBEPL}hotelInformationType" maxOccurs="unbounded" minOccurs="0"/>
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
    "flightInformationAndHotelinformation"
})
public class ItineraryType {

    @XmlElements({
        @XmlElement(name = "flightInformation", namespace = "http://xml.netbeans.org/schema/travelGoodBEPL", type = FlightInformationType.class),
        @XmlElement(name = "hotelinformation", namespace = "", type = HotelInformationType.class)
    })
    protected List<Object> flightInformationAndHotelinformation;

    /**
     * Gets the value of the flightInformationAndHotelinformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightInformationAndHotelinformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightInformationAndHotelinformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightInformationType }
     * {@link HotelInformationType }
     * 
     * 
     */
    public List<Object> getFlightInformationAndHotelinformation() {
        if (flightInformationAndHotelinformation == null) {
            flightInformationAndHotelinformation = new ArrayList<Object>();
        }
        return this.flightInformationAndHotelinformation;
    }

}
