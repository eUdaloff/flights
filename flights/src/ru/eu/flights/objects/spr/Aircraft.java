package ru.eu.flights.objects.spr;

import java.util.Set;

public class Aircraft {

    private long id;
    private String name;
    private String desc;
    private Set<Place> placeList;
    private Set<Place> freePlaceList;

    private Company company;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(Set<Place> placeList) {
        this.placeList = placeList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Place> getFreePlaceList() {
        return freePlaceList;
    }

    public void setFreePlaceList(Set<Place> freePlaceList) {
        this.freePlaceList = freePlaceList;
    }

}
