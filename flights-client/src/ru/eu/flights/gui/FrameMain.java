package ru.eu.flights.gui;


import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXTable;
import ru.eu.flights.client.FlightWSClient;
import ru.eu.flights.client.generated.*;
import ru.eu.flights.gui.models.BoxModel;
import ru.eu.flights.gui.models.FlightTableModel;
import ru.eu.flights.utils.MessageManager;
import ru.eu.flights.ws.proxy.CustomProxySelector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameMain extends JFrame {

    private JButton btSearchFlights;
    private JComboBox comboBoxFrom;
    private JComboBox comboBoxTo;
    private JXDatePicker dateFlight;
    private JPanel rootPanel;
    private JXTable tableFlights;
    private JButton btnBuyTicket;
    private JButton btnCheckReservation;
    private JLabel labelCityFromFlag;
    private JLabel labelCityToFlag;
    private JProgressBar progressBar;

    private FlightWSClient flightWSClient = FlightWSClient.getInstance();
    private List<City> cities;
    private List<Flight> flights = new ArrayList<>();

    public FrameMain() {
        super("Авиабилеты");

        ProxySelector.setDefault(new CustomProxySelector());

        tableFlights.setModel(new FlightTableModel(flights));

        dateFlight.setTimeZone(TimeZone.getTimeZone("GMT"));
        setContentPane(rootPanel);
        fillCities();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btSearchFlights.addActionListener(e -> btnSearchActionPerformed(e));
        btnBuyTicket.addActionListener(e -> btnBuyTicketActionPerformed(e));
        btnCheckReservation.addActionListener(e -> btnCheckReservationActionPerformed());
        comboBoxFrom.addActionListener(e -> comboBoxFromChanged());
        comboBoxTo.addActionListener(e -> comboBoxToChanged());

        showOrHideProgressBar(false);
    }

    private void comboBoxToChanged() {
        City city = (City) comboBoxTo.getSelectedItem();
        byte[] flag = city.getCountry().getFlag();
        if (flag != null) {
            labelCityToFlag.setIcon(new ImageIcon(flag));
        } else {
            labelCityToFlag.setIcon(null);
        }
    }

    private void comboBoxFromChanged() {
        City city = (City) comboBoxFrom.getSelectedItem();
        byte[] flag = city.getCountry().getFlag();
        if (flag != null) {
            labelCityFromFlag.setIcon(new ImageIcon(flag));
        } else {
            labelCityFromFlag.setIcon(null);
        }
    }

    private void btnCheckReservationActionPerformed() {
        DialogCheckReserveCode dialog = new DialogCheckReserveCode(this, true);
        dialog.setVisible(true);
    }

    private void fillCities() {
        cities = flightWSClient.getAllCities();
        comboBoxFrom.setModel(new BoxModel<City>(cities));
        comboBoxTo.setModel(new BoxModel<City>(cities));
    }

    private void btnSearchActionPerformed(ActionEvent e) {
        searchFlights();
    }

    private void btnBuyTicketActionPerformed(ActionEvent e) {
        if (tableFlights.getSelectedRow() >= 0) {
            DialogBuyTicket dialog = new DialogBuyTicket(this, true);
            Flight flight = flights.get(tableFlights.getSelectedRow());
            dialog.setFlight(flight);
            dialog.setVisible(true);
            searchFlights();
        } else {
            MessageManager.showInformMessage(this, "Внимание", "Выберите рейс");
        }
    }

    private void searchFlights() {
        City from = (City) comboBoxFrom.getSelectedItem();
        City to = (City) comboBoxTo.getSelectedItem();
        Date dateDepart = dateFlight.getDate();
        long dateDepartMilliseconds = dateDepart == null ? 0 : dateDepart.getTime();
        showOrHideProgressBar(true);
        flightWSClient.searchFlightsAsyncCallback(dateDepartMilliseconds, from, to, 1, res -> {
            try {
                flights.clear();
                flights.addAll(res.get().getReturn());
            } catch (InterruptedException | ExecutionException e) {
                Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, e);
                MessageManager.showErrorMessage(FrameMain.this, "Ошибка", e.getMessage());
            } finally {
                showOrHideProgressBar(false);
                ((FlightTableModel) tableFlights.getModel()).fireTableDataChanged();
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        } catch (UnsupportedLookAndFeelException ignore) {
        }
        EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }

    private void showOrHideProgressBar(boolean b) {
        progressBar.setVisible(b);
    }
}
