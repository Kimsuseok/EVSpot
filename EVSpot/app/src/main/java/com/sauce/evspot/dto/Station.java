package com.sauce.evspot.dto;

/**
 * Created by Junyoung on 2016-06-27.
 */

public class Station {
    int image;
    String name;
    String address;
    String status;

    public int getImage() {return image;}
    public String getStatus() {return status;}
    public String getAddress() {return address;}
    public String getName() {return name;}

    public Station(int image, String address, String name, String status) {
        this.image = image;
        this.name = name;
        this.address = address;
        this.status = status;
    }
}
