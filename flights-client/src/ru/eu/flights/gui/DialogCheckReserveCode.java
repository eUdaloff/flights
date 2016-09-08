package ru.eu.flights.gui;

import ru.eu.flights.client.FlightWSClient;
import ru.eu.flights.client.generated.Place;
import ru.eu.flights.client.generated.Reservation;
import ru.eu.flights.object.ExtPlace;
import ru.eu.flights.utils.MessageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DialogCheckReserveCode extends JDialog {

    private JPanel contentPane;
    private JButton btnCheckReservation;
    private JButton btnCancel;
    private JTextField txtReservationCode;
    private JProgressBar progressBar;

    private FlightWSClient flightWSClient = FlightWSClient.getInstance();

    public DialogCheckReserveCode(Frame owner, boolean modal) {
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
        setTitle("Проверка брони");
        pack();
        showOrHideProgressBar(false);
    }

    private void btnCheckReservationActionPerformed() {
        String code = txtReservationCode.getText();
        if (code == null || code.isEmpty()) {
            MessageManager.showInformMessage(this, "Внимание", "Укажите код брони");
            return;
        }
        showOrHideProgressBar(true);
        flightWSClient.checkReservationByCodeAsyncCallback(code, res -> {
            try {
                String msg;
                Reservation reservation = res.get().getReturn();
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
                MessageManager.showInformMessage(DialogCheckReserveCode.this, "Результат проверки", msg);
            } catch (InterruptedException | ExecutionException e) {
                Logger.getLogger(DialogCheckReserveCode.class.getName()).log(Level.SEVERE, null, e);
                MessageManager.showErrorMessage(this, "Ошибка", e.getMessage());
            }
            showOrHideProgressBar(false);
        });
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
        DialogCheckReserveCode dialog = new DialogCheckReserveCode(null, false);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void showOrHideProgressBar(boolean b) {
        progressBar.setVisible(b);
    }
}
