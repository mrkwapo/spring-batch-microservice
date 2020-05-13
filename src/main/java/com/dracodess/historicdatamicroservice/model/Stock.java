package com.dracodess.historicdatamicroservice.model;


//import java.util.Date;

public class Stock{
    //fields
    long id;
    String symbol;
    Double open_price;
    Double close_price;
    String date_of_record;

    //Default Constructor
    public Stock() {
    }

    //Constructor Overload

    public Stock(long id, String symbol, Double open_price, Double close_price, String date_of_record) {
        this.id = id;
        this.symbol = symbol;
        this.open_price = open_price;
        this.close_price = close_price;
        this.date_of_record = date_of_record;
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getOpen_price() {
        return open_price;
    }

    public void setOpen_price(Double open_price) {
        this.open_price = open_price;
    }

    public Double getClose_price() {
        return close_price;
    }

    public void setClose_price(Double close_price) {
        this.close_price = close_price;
    }

    public String getDate_of_record() {
        return date_of_record;
    }

    public void setDate_of_record(String date_of_record) {
        this.date_of_record = date_of_record;
    }

    @Override
    public String toString() {
        return String.format("Stock[id=%d, symbol=%s, open_price=%f, close_price=%f, date_of_record=%s ]", id, symbol, open_price, close_price, date_of_record);
    }
}
