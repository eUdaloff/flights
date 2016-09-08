package ru.eu.flights.gui;

import ru.eu.flights.client.FlightWSClient;
import ru.eu.flights.client.generated.*;
import ru.eu.flights.gui.models.BoxModel;
import ru.eu.flights.object.ExtPlace;
import ru.eu.flights.utils.MessageManager;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DialogBuyTicket extends JDialog {

    private FlightWSClient flightWSClient = FlightWSClient.getInstance();
    private Flight flight;

    private JPanel contentPane;
    private JButton btnBuyTicket;
    private JButton btnCancel;
    private JTextField txtFamilyName;
    private JTextField txtDocNum;
    private JTextField txtName;
    private JTextField txtMiddleName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtCardNum;
    private JTextArea txtAreaAddInfo;
    private JComboBox comboBoxPlaces;
    private JProgressBar progressBar;

    public DialogBuyTicket(JFrame parent, boolean modal) {
        super(parent, modal);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnBuyTicket);
        btnBuyTicket.addActionListener(e -> btnBuyTicketActionPerformed());
        btnCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setTitle("Покупка билета");
        pack();
        showOrHideProgressBar(false);
    }

    private void btnBuyTicketActionPerformed() {
        Passenger passenger = new Passenger();
        passenger.setGivenName(txtName.getText());
        passenger.setFamilyName(txtFamilyName.getText());
        passenger.setMiddleName(txtMiddleName.getText());
        passenger.setDocumentNumber(txtDocNum.getText());
        passenger.setEmail(txtEmail.getText());
        passenger.setPhone(txtPhone.getText());
        Place place = (Place) comboBoxPlaces.getSelectedItem();
        showOrHideProgressBar(true);
        flightWSClient.buyTicketAsyncCallback(flight, place, passenger, txtAreaAddInfo.getText(), res -> {
            try {
                if (res.get().isReturn()) {
                    MessageManager.showInformMessage(DialogBuyTicket.this, "Покупка билета", "Все хорошо. Куплен.");
                    dispose();
                } else {
                    MessageManager.showErrorMessage(DialogBuyTicket.this, "Покупка билета", "Ошибка. Не куплен.");
                }
            } catch (InterruptedException | ExecutionException e) {
                Logger.getLogger(DialogBuyTicket.class.getName()).log(Level.SEVERE, null, e);
                MessageManager.showErrorMessage(this, "Ошибка", e.getMessage());
            } finally {
                showOrHideProgressBar(false);
            }
        });

//        try {
//            flightWSClient.buyTicket(flight, place, passenger, txtAreaAddInfo.getText());
//        } catch (InvalidArgumentMN e) {
//            Logger.getLogger(DialogBuyTicket.class.getName()).log(Level.SEVERE, null, e);
//            MessageManager.showErrorMessage(this, "Ошибка", e.getMessage());
//            return;
//        }

    }

    private void onCancel() {
        dispose();
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
        fillPlaces();
    }

    private void fillPlaces() {
        List<ExtPlace> freePlaces = new ArrayList<>();

        for (Place place : flight.getAircraft().getFreePlaceList()) {
            ExtPlace ep = new ExtPlace();
            ep.setId(place.getId());
            ep.setBusy(place.isBusy());
            ep.setFlightClass(place.getFlightClass());
            ep.setSeatLetter(place.getSeatLetter());
            ep.setSeatNumber(place.getSeatNumber());
            freePlaces.add(ep);
        }
        comboBoxPlaces.setModel(new BoxModel<>(freePlaces));
    }

    private void showOrHideProgressBar(boolean b) {
        progressBar.setVisible(b);
    }
}
