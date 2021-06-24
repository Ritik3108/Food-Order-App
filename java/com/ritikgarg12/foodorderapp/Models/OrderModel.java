package com.ritikgarg12.foodorderapp.Models;

public class OrderModel {

    int soldItemImage;
    String soldItemName, soldItemPrice, soldItemorderNumber;



    public OrderModel() {

    }

    public int getSoldItemImage()
    {
        return soldItemImage;
    }

    public void setSoldItemImage(int soldItemImage)
    {
        this.soldItemImage = soldItemImage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getSoldItemPrice() {
        return soldItemPrice;
    }

    public void setSoldItemPrice(String soldItemPrice) {
        this.soldItemPrice = soldItemPrice;
    }

    public String getSoldItemorderNumber() {
        return soldItemorderNumber;
    }

    public void setSoldItemorderNumber(String soldItemorderNumber) {
        this.soldItemorderNumber = soldItemorderNumber;
    }
}
