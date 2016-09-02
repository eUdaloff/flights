package ru.eu.flights.gui;

import ru.eu.flights.client.FlightWSClient;
import ru.eu.flights.client.generated.InvalidArgumentMN;
import ru.eu.flights.client.generated.Place;
import ru.eu.flights.client.generated.Reservation;
import ru.eu.flights.object.ExtPlace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DialogCheckReservCode extends JDialog {

    private JPanel contentPane;
    private JButton btnCheckReservation;
    private JButton btnCancel;
    private JTextField txtReservationCode;

    public DialogCheckReservCode(Frame owner, boolean modal) {
        super(owner, modal);
        setContentPane(contentPane);
        getRootPane().setDefaultButton(btnCheckReservation);

        btnCheckReservation.addActionListener(e -> btnCheckReservationActionPerformed());

        btnCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void btnCheckReservationActionPerformed() {
        String code = txtReservationCode.getText();
        if (code == null || code.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Укажите код брони", "Внимание", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        Reservation reservation = null;
        try {
            reservation = FlightWSClient.getInstance().checkReservationByCode(code);
        } catch (InvalidArgumentMN e) {
            Logger.getLogger(DialogCheckReservCode.class.getName()).log(Level.SEVERE, null, e);
            return;
        }
        String msg;
        if (reservation != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Рейс: ").append(reservation.getFlight().getCode()).append("\n");
            sb.append("Самолет: ").append(reservation.getFlight().getAircraft().getName()).append("\n");
            sb.append("Дата вылета: ").append(reservation.getFlight().getDateDepart()).append("\n");
            sb.append("Дата прилета: ").append(reservation.getFlight().getDateArrive()).append("\n");
            sb.append("Место: ").append(transformToExtPlace(reservation.getPlace())).append("\n");
            msg = sb.toString();
        } else {
            msg = "Бронь не найдена";
        }
        JOptionPane.showMessageDialog(this, msg, "Результат проверки", JOptionPane.PLAIN_MESSAGE);
    }

    private ExtPlace transformToExtPlace(Place place) {
        ExtPlace ep = new ExtPlace();
        ep.setId(place.getId());
        ep.setBusy(place.isBusy());
        ep.setFlightClass(place.getFlightClass());
        ep.setSeatLetter(place.getSeatLetter());
        ep.setSeatNumber(place.getSeatNumber());
        return ep;
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        DialogCheckReservCode dialog = new DialogCheckReservCode(null, false);
        dialog.pack();
        dialog.setVisible(true);

    }
}
