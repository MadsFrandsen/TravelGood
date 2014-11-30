
package flightlist.travelgood.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for flight complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="flight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fromAirport" type="{ws.travelgood.flightlist}airport"/>
 *         &lt;element name="toAirport" type="{ws.travelgood.flightlist}airport"/>
 *         &lt;element name="timeTakeOff" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="timeLanding" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="carrier" type="{ws.travelgood.flightlist}carrier"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "flight", propOrder = {
    "fromAirport",
    "toAirport",
    "timeTakeOff",
    "timeLanding",
    "carrier"
})
public class Flight {

    @XmlElement(required = true)
    protected String fromAirport;
    @XmlElement(required = true)
    protected String toAirport;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeTakeOff;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeLanding;
    @XmlElement(required = true)
    protected String carrier;

    /**
     * Gets the value of the fromAirport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromAirport() {
        return fromAirport;
    }

    /**
     * Sets the value of the fromAirport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromAirport(String value) {
        this.fromAirport = value;
    }

    /**
     * Gets the value of the toAirport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToAirport() {
        return toAirport;
    }

    /**
     * Sets the value of the toAirport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToAirport(String value) {
        this.toAirport = value;
    }

    /**
     * Gets the value of the timeTakeOff property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeTakeOff() {
        return timeTakeOff;
    }

    /**
     * Sets the value of the timeTakeOff property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeTakeOff(XMLGregorianCalendar value) {
        this.timeTakeOff = value;
    }

    /**
     * Gets the value of the timeLanding property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeLanding() {
        return timeLanding;
    }

    /**
     * Sets the value of the timeLanding property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeLanding(XMLGregorianCalendar value) {
        this.timeLanding = value;
    }

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrier(String value) {
        this.carrier = value;
    }

}
