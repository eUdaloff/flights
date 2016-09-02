package ru.eu.flights.object;


import ru.eu.flights.client.generated.City;

public class ExtCity extends City {

    @Override
    public String toString() {
        return getName();
    }
}
