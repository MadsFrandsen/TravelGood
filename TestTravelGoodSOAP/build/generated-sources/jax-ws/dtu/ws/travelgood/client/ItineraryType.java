
package dtu.ws.travelgood.client;

import java.util.ArrayList;
import java.util.List;
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
    "flightInformation",
    "hotelinformation"
})
public class ItineraryType {

    @XmlElement(namespace = "http://xml.netbeans.org/schema/travelGoodBEPL")
    protected List<FlightInformationType> flightInformation;
    @XmlElement(namespace = "")
    protected List<HotelInformationType> hotelinformation;

    /**
     * Gets the value of the flightInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the flightInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFlightInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FlightInformationType }
     * 
     * 
     */
    public List<FlightInformationType> getFlightInformation() {
        if (flightInformation == null) {
            flightInformation = new ArrayList<FlightInformationType>();
        }
        return this.flightInformation;
    }

    /**
     * Gets the value of the hotelinformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hotelinformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHotelinformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HotelInformationType }
     * 
     * 
     */
    public List<HotelInformationType> getHotelinformation() {
        if (hotelinformation == null) {
            hotelinformation = new ArrayList<HotelInformationType>();
        }
        return this.hotelinformation;
    }

}
