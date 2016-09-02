package ru.eu.flights.objects;

import ru.eu.flights.objects.spr.Aircraft;
import ru.eu.flights.objects.spr.City;
import ru.eu.flights.ws.annotations.ExceptionMessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Calendar;

@XmlAccessorType(XmlAccessType.FIELD)
@ExceptionMessage(message = "Не указан рейс")
public class Flight {

    private long id;
    private String code;

    @ExceptionMessage(message = "Не указана дата вылета")
    private Calendar dateDepart;

    private Calendar dateArrive;
    private Aircraft aircraft;

    @ExceptionMessage(message = "Не указан город вылета")
    private City cityFrom;

    @ExceptionMessage(message = "Не указан город прибытия")
    private City cityTo;
    private String duration;
    private boolean existFreePlaces;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Calendar getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Calendar dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Calendar getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Calendar dateArrive) {
        this.dateArrive = dateArrive;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isExistFreePlaces() {
        return existFreePlaces;
    }

    public void setExistFreePlaces(boolean existFreePlaces) {
        this.existFreePlaces = existFreePlaces;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", dateDepart=" + dateDepart +
                ", dateArrive=" + dateArrive +
                ", aircraft=" + aircraft +
                ", cityFrom=" + cityFrom +
                ", cityTo=" + cityTo +
                ", duration='" + duration + '\'' +
                ", existFreePlaces=" + existFreePlaces +
                '}';
    }

}
