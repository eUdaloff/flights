package ru.eu.flights.servlets;

import ru.eu.flights.database.*;
import ru.eu.flights.objects.Flight;
import ru.eu.flights.objects.Passenger;
import ru.eu.flights.objects.Reservation;
import ru.eu.flights.objects.spr.Place;
import ru.eu.flights.utils.GMTCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;


@WebServlet(name = "TestSearch", urlPatterns = "/TestSearch")
public class TestSearch extends HttpServlet {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //City c = CityDB.getInstance().getCity(2);
        //out.println(c);

//        Aircraft a = AircraftDB.getInstance().getAircraft(1);
//        out.println(a);

//        Flight f = FlightDB.getInstance().getFlight(1);
//        out.println(f);

//        Calendar date = GMTCalendar.getInstance();
//        date.setTimeInMillis(1463666400000l);
//        out.println("WHEN: ");
//        out.println(sdf.format(date.getTime()));
//
//        City from  = CityDB.getInstance().getCity(1);
//        out.println("FROM: ");
//        out.println(from.getName());
//        City to  = CityDB.getInstance().getCity(2);
//        out.println("TO: ");
//        out.println(to.getName());
//
//        Set<Flight> flights = FlightDB.getInstance().getFlights(date, from, to);
//        for(Flight f : flights) {
//            out.println("\n" + f);
//        }

//        Passenger p = PassengerDB.getInstance().getPassenger(1);
//
//        Flight f = FlightDB.getInstance().getFlight(1);
//
//        Place pl = PlaceDB.getInstance().getPlace(5);
//
//        Reservation res = new Reservation();
//        res.setFlight(f);
//        res.setPassenger(p);
//        res.setPlace(pl);
//        res.setAddInfo("Add info");
//        Calendar c = GMTCalendar.getInstance();
//        c.setTimeInMillis(1462026600000l);
//        res.setReserveDateTime(c);
//        res.setCode(UUID.randomUUID().toString());
//
//        ReservationDB.getInstance().insertReservation(res);
//
//        out.println("Inserted");

        try {
            Reservation reservation = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByCode("4f68a008-41ca-42d6-9c6e-47f150006d20"));
            System.out.println(reservation.getFlight().getAircraft().getName());

//            Calendar c = GMTCalendar.getInstance();
//            c.setTimeInMillis(1462026600000l);
//            reservation = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByDateReserv(c));
//            System.out.println(reservation.getFlight().getAircraft().getName());
//
//            reservation = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByDocumentNumber("0000111222"));
//            System.out.println(reservation.getFlight().getAircraft().getName());
//
//            reservation = ReservationDB.getInstance().executeObject(ReservationDB.getInstance().getStmtByFamilyName("Ivanovich"));
//            System.out.println(reservation.getFlight().getAircraft().getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
