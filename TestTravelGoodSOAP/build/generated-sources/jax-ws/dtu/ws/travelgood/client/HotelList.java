
package dtu.ws.travelgood.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hotelList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hotelList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newElement" type="{http://xml.netbeans.org/schema/travelGoodBEPL}hotelInformationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hotelList", namespace = "http://travelgood.ws", propOrder = {
    "newElement"
})
public class HotelList {

    @XmlElement(namespace = "", required = true)
    protected HotelInformationType newElement;

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
