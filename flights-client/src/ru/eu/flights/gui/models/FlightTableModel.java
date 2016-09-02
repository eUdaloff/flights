package ru.eu.flights.gui.models;

import ru.eu.flights.client.generated.Flight;
import ru.eu.flights.client.generated.Place;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class FlightTableModel extends AbstractTableModel {

    public static final int COLUMN_COUNT = 6;
    private List<Flight> list;
    private SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

    public FlightTableModel(List<Flight> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Flight flight = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return flight.getCode();
            case 1:
                return formatDate.format(flight.getDateDepart().toGregorianCalendar().getTime());
            case 2:
                return formatDate.format(flight.getDateArrive().toGregorianCalendar().getTime());
            case 3:
                return flight.getAircraft().getName();
            case 4:
                return flight.getDuration();
            case 5:
                return getFreeCount(flight);
        }
        return "";
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Код";
            case 1:
                return "Дата вылета";
            case 2:
                return "Дата прилета";
            case 3:
                return "Самолет";
            case 4:
                return "Длительность";
            case 5:
                return "Количество мест";
        }
        return "";
    }

    private int getFreeCount(Flight flight) {
        int count = 0;
        for (Place place : flight.getAircraft().getPlaceList()) {
            if (!place.isBusy()) {
                count++;
            }
        }

        return count;
    }

    public Flight getFlight(int idx) {
        if (list != null && idx >= 0 && idx < list.size())
            return list.get(idx);
        return null;
    }

}
