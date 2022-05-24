package com.example.prj3;

public class Vert implements Comparable<Vert>{

    String city;
    double dist;
    Vert parent;
    double fulldis;

    public Vert getParent() {
        return parent;
    }

    public void setParent(Vert parent) {
        this.parent = parent;
    }

    public double getFulldis() {
        return fulldis;
    }

    public void setFulldis(double fulldis) {
        this.fulldis = fulldis;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    @Override
    public String toString() {
        return "Vert{" +
                "city='" + city + '\'' +
                ", dist=" + dist +
                '}';
    }
    @Override
    public int compareTo(Vert vert) {

        return Double.compare(this.fulldis,vert.fulldis);
    }

}
