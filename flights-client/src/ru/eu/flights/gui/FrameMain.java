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
import java.awt.event.ActionListener;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
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

    private FlightWSClient flightWSClient = FlightWSClient.getInstance();
    private List<City> cities;
    private List<Flight> flights;

    public FrameMain() {
        super("Авиабилеты");

        ProxySelector.setDefault(new CustomProxySelector());

        tableFlights.setModel(new FlightTableModel(new ArrayList<Flight>()));

        dateFlight.setTimeZone(TimeZone.getTimeZone("GMT"));
        setContentPane(rootPanel);
        fillCities();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btSearchFlights.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSearchActionPerformed(e);
            }
        });

        btnBuyTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnBuyTicketActionPerformed(e);
            }
        });

        btnCheckReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCheckReservationActionPerformed();
            }
        });
        comboBoxFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxFromChanged();
            }
        });
        comboBoxTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxToChanged();
            }
        });
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
        DialogCheckReservCode dialog = new DialogCheckReservCode(this, true);
        dialog.pack();
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
            dialog.pack();
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
        flights = new ArrayList<>();
        try {
            flights.addAll(flightWSClient.searchFlights(dateDepartMilliseconds, from, to, 1));
        } catch (InvalidArgumentMN e) {
            Logger.getLogger(FrameMain.class.getName()).log(Level.SEVERE, null, e);
            MessageManager.showErrorMessage(this, "Ошибка", e.getMessage());
            return;
        }
        tableFlights.setModel(new FlightTableModel(flights));
        ((FlightTableModel) tableFlights.getModel()).fireTableDataChanged();
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        } catch (UnsupportedLookAndFeelException ignore) {
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }
}
