
package ru.eu.flights.client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.eu.flights.client.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllCities_QNAME = new QName("http://flights.eu.ru/ws", "getAllCities");
    private final static QName _BuyTicket_QNAME = new QName("http://flights.eu.ru/ws", "buyTicket");
    private final static QName _GetAllCitiesResponse_QNAME = new QName("http://flights.eu.ru/ws", "getAllCitiesResponse");
    private final static QName _SearchFlights_QNAME = new QName("http://flights.eu.ru/ws", "searchFlights");
    private final static QName _BuyTicketResponse_QNAME = new QName("http://flights.eu.ru/ws", "buyTicketResponse");
    private final static QName _CheckReservationByCodeResponse_QNAME = new QName("http://flights.eu.ru/ws", "checkReservationByCodeResponse");
    private final static QName _SearchFlightsResponse_QNAME = new QName("http://flights.eu.ru/ws", "searchFlightsResponse");
    private final static QName _InvalidArgument_QNAME = new QName("http://flights.eu.ru/ws", "InvalidArgument");
    private final static QName _CheckReservationByCode_QNAME = new QName("http://flights.eu.ru/ws", "checkReservationByCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.eu.flights.client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuyTicketResponse }
     * 
     */
    public BuyTicketResponse createBuyTicketResponse() {
        return new BuyTicketResponse();
    }

    /**
     * Create an instance of {@link CheckReservationByCodeResponse }
     * 
     */
    public CheckReservationByCodeResponse createCheckReservationByCodeResponse() {
        return new CheckReservationByCodeResponse();
    }

    /**
     * Create an instance of {@link SearchFlightsResponse }
     * 
     */
    public SearchFlightsResponse createSearchFlightsResponse() {
        return new SearchFlightsResponse();
    }

    /**
     * Create an instance of {@link CheckReservationByCode }
     * 
     */
    public CheckReservationByCode createCheckReservationByCode() {
        return new CheckReservationByCode();
    }

    /**
     * Create an instance of {@link ArgumentException }
     * 
     */
    public ArgumentException createArgumentException() {
        return new ArgumentException();
    }

    /**
     * Create an instance of {@link BuyTicket }
     * 
     */
    public BuyTicket createBuyTicket() {
        return new BuyTicket();
    }

    /**
     * Create an instance of {@link GetAllCities }
     * 
     */
    public GetAllCities createGetAllCities() {
        return new GetAllCities();
    }

    /**
     * Create an instance of {@link SearchFlights }
     * 
     */
    public SearchFlights createSearchFlights() {
        return new SearchFlights();
    }

    /**
     * Create an instance of {@link GetAllCitiesResponse }
     * 
     */
    public GetAllCitiesResponse createGetAllCitiesResponse() {
        return new GetAllCitiesResponse();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link City }
     * 
     */
    public City createCity() {
        return new City();
    }

    /**
     * Create an instance of {@link Aircraft }
     * 
     */
    public Aircraft createAircraft() {
        return new Aircraft();
    }

    /**
     * Create an instance of {@link FlightClass }
     * 
     */
    public FlightClass createFlightClass() {
        return new FlightClass();
    }

    /**
     * Create an instance of {@link Passenger }
     * 
     */
    public Passenger createPassenger() {
        return new Passenger();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link Company }
     * 
     */
    public Company createCompany() {
        return new Company();
    }

    /**
     * Create an instance of {@link Place }
     * 
     */
    public Place createPlace() {
        return new Place();
    }

    /**
     * Create an instance of {@link ExceptionInfo }
     * 
     */
    public ExceptionInfo createExceptionInfo() {
        return new ExceptionInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCities }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "getAllCities")
    public JAXBElement<GetAllCities> createGetAllCities(GetAllCities value) {
        return new JAXBElement<GetAllCities>(_GetAllCities_QNAME, GetAllCities.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "buyTicket")
    public JAXBElement<BuyTicket> createBuyTicket(BuyTicket value) {
        return new JAXBElement<BuyTicket>(_BuyTicket_QNAME, BuyTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "getAllCitiesResponse")
    public JAXBElement<GetAllCitiesResponse> createGetAllCitiesResponse(GetAllCitiesResponse value) {
        return new JAXBElement<GetAllCitiesResponse>(_GetAllCitiesResponse_QNAME, GetAllCitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFlights }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "searchFlights")
    public JAXBElement<SearchFlights> createSearchFlights(SearchFlights value) {
        return new JAXBElement<SearchFlights>(_SearchFlights_QNAME, SearchFlights.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "buyTicketResponse")
    public JAXBElement<BuyTicketResponse> createBuyTicketResponse(BuyTicketResponse value) {
        return new JAXBElement<BuyTicketResponse>(_BuyTicketResponse_QNAME, BuyTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckReservationByCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "checkReservationByCodeResponse")
    public JAXBElement<CheckReservationByCodeResponse> createCheckReservationByCodeResponse(CheckReservationByCodeResponse value) {
        return new JAXBElement<CheckReservationByCodeResponse>(_CheckReservationByCodeResponse_QNAME, CheckReservationByCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFlightsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "searchFlightsResponse")
    public JAXBElement<SearchFlightsResponse> createSearchFlightsResponse(SearchFlightsResponse value) {
        return new JAXBElement<SearchFlightsResponse>(_SearchFlightsResponse_QNAME, SearchFlightsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArgumentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "InvalidArgument")
    public JAXBElement<ArgumentException> createInvalidArgument(ArgumentException value) {
        return new JAXBElement<ArgumentException>(_InvalidArgument_QNAME, ArgumentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckReservationByCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flights.eu.ru/ws", name = "checkReservationByCode")
    public JAXBElement<CheckReservationByCode> createCheckReservationByCode(CheckReservationByCode value) {
        return new JAXBElement<CheckReservationByCode>(_CheckReservationByCode_QNAME, CheckReservationByCode.class, null, value);
    }

}
