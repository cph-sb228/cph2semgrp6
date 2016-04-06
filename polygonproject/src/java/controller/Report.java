/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author terfy
 */
public class Report {
    private int id;
    private String filedir;
    private int buildingID;
    private String buildingcondition;

    public Report(String filedir, int buildingID, String buildingcondition) {
        this.filedir = filedir;
        this.buildingID = buildingID;
        this.buildingcondition = buildingcondition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFiledir() {
        return filedir;
    }

    public void setFiledir(String filedir) {
        this.filedir = filedir;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingcondition() {
        return buildingcondition;
    }

    public void setBuildingcondition(String buildingcondition) {
        this.buildingcondition = buildingcondition;
    }


}
