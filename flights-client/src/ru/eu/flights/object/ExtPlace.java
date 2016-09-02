package ru.eu.flights.object;


import ru.eu.flights.client.generated.Place;

public class ExtPlace extends Place {
    @Override
    public String toString() {

        return String.valueOf(new Character((char) getSeatLetter())) + String.valueOf(getSeatNumber());
    }
}
