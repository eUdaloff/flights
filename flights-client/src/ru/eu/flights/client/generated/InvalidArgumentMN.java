
package ru.eu.flights.client.generated;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InvalidArgument", targetNamespace = "http://flights.eu.ru/ws")
public class InvalidArgumentMN
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ArgumentException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InvalidArgumentMN(String message, ArgumentException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InvalidArgumentMN(String message, ArgumentException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ru.eu.flights.client.generated.ArgumentException
     */
    public ArgumentException getFaultInfo() {
        return faultInfo;
    }

}
