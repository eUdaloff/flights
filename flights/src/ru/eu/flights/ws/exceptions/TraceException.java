package ru.eu.flights.ws.exceptions;


public class TraceException extends Exception {

    private ExceptionInfo exceptionInfo;


    public TraceException(String message) {
        super(message);
        StringBuilder sb = new StringBuilder();
        for(StackTraceElement e: getStackTrace()) {
            sb.append(e.toString()).append('\n');
        }
        exceptionInfo = new ExceptionInfo(sb.toString());
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
