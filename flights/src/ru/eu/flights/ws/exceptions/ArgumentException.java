package ru.eu.flights.ws.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "InvalidArgument", messageName = "InvalidArgumentMN")
public class ArgumentException extends TraceException {

    public ArgumentException(String message) {
        super(message);
    }
}
