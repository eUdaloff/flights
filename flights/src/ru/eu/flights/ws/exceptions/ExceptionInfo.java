package ru.eu.flights.ws.exceptions;

public class ExceptionInfo {

    private String trace;

    public ExceptionInfo() {
    }

    public ExceptionInfo(String trace) {
        this.trace = trace;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }
}
