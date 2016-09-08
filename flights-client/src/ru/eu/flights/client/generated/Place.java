
package ru.eu.flights.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for place complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="place">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="seatLetter" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *         &lt;element name="seatNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="flightClass" type="{http://flights.eu.ru/ws}flightClass" minOccurs="0"/>
 *         &lt;element name="busy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "place", propOrder = {
    "id",
    "seatLetter",
    "seatNumber",
    "flightClass",
    "busy"
})
public class Place {

    protected long id;
    @XmlSchemaType(name = "unsignedShort")
    protected int seatLetter;
    protected int seatNumber;
    protected FlightClass flightClass;
    protected boolean busy;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the seatLetter property.
     * 
     */
    public int getSeatLetter() {
        return seatLetter;
    }

    /**
     * Sets the value of the seatLetter property.
     * 
     */
    public void setSeatLetter(int value) {
        this.seatLetter = value;
    }

    /**
     * Gets the value of the seatNumber property.
     * 
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Sets the value of the seatNumber property.
     * 
     */
    public void setSeatNumber(int value) {
        this.seatNumber = value;
    }

    /**
     * Gets the value of the flightClass property.
     * 
     * @return
     *     possible object is
     *     {@link FlightClass }
     *     
     */
    public FlightClass getFlightClass() {
        return flightClass;
    }

    /**
     * Sets the value of the flightClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlightClass }
     *     
     */
    public void setFlightClass(FlightClass value) {
        this.flightClass = value;
    }

    /**
     * Gets the value of the busy property.
     * 
     */
    public boolean isBusy() {
        return busy;
    }

    /**
     * Sets the value of the busy property.
     * 
     */
    public void setBusy(boolean value) {
        this.busy = value;
    }

}
