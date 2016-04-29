/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;

/**
 *
 * @author terfy
 */
public class Report {
    
    private int id;
    private int buildingID;
    private String itemname;
    private String itemproblem;
    private String floor;
    private String roomnumber;
    private String importancy;
    private String comments;
    private List<String> filenames;

    public Report(String itemname, String itemproblem, String floor, String roomnumber, String importancy, String comments) {
        this.itemname = itemname;
        this.itemproblem = itemproblem;
        this.floor = floor;
        this.roomnumber = roomnumber;
        this.importancy = importancy;
        this.comments = comments;
    }

    public List<String> getFilenames() {
        return filenames;
    }

    public void setFilenames(List<String> filenames) {
        this.filenames = filenames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemproblem() {
        return itemproblem;
    }

    public void setItemproblem(String itemproblem) {
        this.itemproblem = itemproblem;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public String getImportancy() {
        return importancy;
    }

    public void setImportancy(String importancy) {
        this.importancy = importancy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    

}
