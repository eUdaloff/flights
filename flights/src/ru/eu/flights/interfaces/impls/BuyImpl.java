package ru.eu.flights.interfaces.impls;


import ru.eu.flights.database.PassengerDB;
import ru.eu.flights.database.ReservationDB;
import ru.eu.flights.interfaces.Buy;
import ru.eu.flights.objects.Flight;
import ru.eu.flights.objects.Passenger;
import ru.eu.flights.objects.Reservation;
import ru.eu.flights.objects.spr.Place;
import ru.eu.flights.utils.GMTCalendar;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyImpl implements Buy {

    private ReservationDB reservationDB = ReservationDB.getInstance();
    private PassengerDB passengerDB = PassengerDB.getInstance();

    @Override
    public boolean buyTicket(Passenger passenger, Flight flight, Place place, String addInfo) {
        Reservation reservation = new Reservation();
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setPlace(place);
        reservation.setAddInfo(addInfo);
        reservation.setCode(UUID.randomUUID().toString());
        Calendar cal = GMTCalendar.getInstance();
        reservation.setReserveDateTime(cal);
        try {
            int id = passengerDB.insert(passengerDB.getInsertStmt(passenger));
            passenger.setId(id);
            reservationDB.insert(reservationDB.getInsertStmt(reservation));
        } catch (SQLException e) {
            Logger.getLogger(BuyImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
        return true;
    }
}
