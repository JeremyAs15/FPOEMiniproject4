package com.example.fpoeminiproject4.models;
import com.example.fpoeminiproject4.interfaces.ShipInterface;

public class Ship implements ShipInterface {
    int shipSize;
    int shipType;
    int shipAmount;

    public Ship(int shipType, int shipSize, int shipAmount) {
        this.shipSize = shipSize;
        this.shipType = shipType;
        this.shipAmount = shipAmount;
    }

    @Override
    public int getShipSize() {
        return shipSize;
    }

    @Override
    public void setShipSize(int shipSize) {
        this.shipSize = shipType;
    }

    @Override
    public int getShipType() {
        return shipType;
    }

    @Override
    public void setShipType(int shipType) {
        this.shipType = shipType;
    }

    @Override
    public int getShipAmount() {
        return shipAmount;
    }

    @Override
    public void setShipAmount(int shipAmount) {
        this.shipAmount = shipAmount;
    }
}