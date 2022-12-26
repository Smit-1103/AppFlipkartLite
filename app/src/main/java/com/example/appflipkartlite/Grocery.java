package com.example.appflipkartlite;

public class Grocery {
    String gid;
    String imgUrl;
    String name;
    int price;
    int stock;
    String unit;

    //default
    public Grocery(String gid) {
        this.gid = gid;
    }

    public Grocery()
    {}

    //parameterised
    public Grocery(String gid, String imgUrl, String name, int price, int stock, String unit) {
        this.gid = gid;
        this.imgUrl = imgUrl;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.unit = unit;
    }

    //a data print thay ...jyare object bane to tostring method call thay automatic and data batave badha

    public String toString(){
        return " NAME = "+name+" , PRICE = "+price+" , Stock = "+stock;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

//steps for pojo class or model class
//default constructor  +  parameterised constructor bane pojo joye
