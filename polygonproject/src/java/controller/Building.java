/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author terfy eller hvemmdfmdfn
 */
public class Building {
    private int id;
    private String owner;
    private String address;
    private int housenr;
    private int zipcode;
    private String city;

    public int getHousenr() {
        return housenr;
    }

    public void setHousenr(int housenr) {
        this.housenr = housenr;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getKm2() {
        return km2;
    }

    public void setKm2(int km2) {
        this.km2 = km2;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    private int floor;
    private int km2;
    private String conditions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Building(String owner, String address, int housenr, int zipcode, String city, int floor, int km2, String conditions) {
        this.owner = owner;
        this.address = address;
        this.housenr = housenr;
        this.zipcode = zipcode;
        this.city = city;
        this.floor = floor;
        this.km2 = km2;
        this.conditions = conditions;
    }
    
    
}