package ru.eu.flights.interfaces.impls;


import ru.eu.flights.database.ReservationDB;
import ru.eu.flights.interfaces.Check;
import ru.eu.flights.objects.Reservation;
import ru.eu.flights.utils.GMTCalendar;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckImpl implements Check {

    private ReservationDB reservationDB = ReservationDB.getInstance();

    @Override
    public Reservation checkReservationByCode(String code) {
        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByCode(code));
        } catch (SQLException e) {
            Logger.getLogger(CheckImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return reservation;
    }

    @Override
    public Reservation checkReservationByDate(long date) {
        Calendar c = GMTCalendar.getInstance();
        c.setTimeInMillis(date);

        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByDateReserv(c));
        } catch (SQLException e) {
            Logger.getLogger(CheckImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return reservation;
    }

    @Override
    public Reservation checkReservationByDocNumber(String docNumber) {
        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByDocumentNumber(docNumber));
        } catch (SQLException e) {
            Logger.getLogger(CheckImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return reservation;
    }

    @Override
    public Reservation checkReservationByFamilyName(String fn) {
        Reservation reservation = null;
        try {
            reservation = reservationDB.executeObject(reservationDB.getStmtByFamilyName(fn));
        } catch (SQLException e) {
            Logger.getLogger(CheckImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return reservation;
    }

}
