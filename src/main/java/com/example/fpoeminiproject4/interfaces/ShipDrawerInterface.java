package com.example.fpoeminiproject4.interfaces;

import javafx.scene.Group;

public interface ShipDrawerInterface {

    Group drawFrigate(boolean vertical);
    Group drawDestroyer(boolean vertical, boolean insideGrid);
    Group drawSubmarine(boolean vertical, boolean insideGrid);
    Group drawAircraftCarrier(boolean vertical, boolean insideGrid);
}