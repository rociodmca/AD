package com.example;

import java.util.List;

public class FootballPlayer {
    private int dorsal;
    private String name;
    private List demarcation;
    private String team;

    public FootballPlayer() {

    }

    public FootballPlayer(int dorsal, String name, List demarcation, String team) {
        this.dorsal = dorsal;
        this.name = name;
        this.demarcation = demarcation;
        this.team = team;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getListDemarcation() {
        return demarcation;
    }

    public void setListDemarcation(List listDemarcation) {
        this.demarcation = listDemarcation;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "FootballPlayer [dorsal=" + dorsal + ", name=" + name + ", listDemarcation=" + demarcation
                + ", team=" + team + "]";
    }
    
}
