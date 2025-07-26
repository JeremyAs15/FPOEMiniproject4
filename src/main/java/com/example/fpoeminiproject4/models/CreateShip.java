package com.example.fpoeminiproject4.models;

import com.example.fpoeminiproject4.interfaces.ShipDrawerInterface;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class CreateShip implements ShipDrawerInterface {
    private final double SHIP_HEIGHT = 22.0;
    private final int CELL_SIZE = 30;

    @Override
    public Group drawFrigate(boolean vertical) {
        double shipLength = this.CELL_SIZE;
        Path mainContainer = createShipOutline(shipLength);
        mainContainer.setFill(Color.web("#D9D9D9"));
        mainContainer.setStroke(Color.web("#61697A"));

        Group frigateGroup = new Group(mainContainer);
        if (vertical) {
            frigateGroup.setRotate(90);
        }
        return frigateGroup;
    }

    @Override
    public Group drawDestroyer(boolean vertical, boolean insideGrid) {
        double shipLength = this.CELL_SIZE * 2;
        Path mainContainer = createShipOutline(shipLength);
        mainContainer.setFill(Color.web("#D9D9D9"));
        mainContainer.setStroke(Color.web("#61697A"));

        Group destroyerGroup = new Group(mainContainer);
        if (vertical) {
            destroyerGroup.setRotate(90);
            if (insideGrid) {
                destroyerGroup.setTranslateX(-16);
                destroyerGroup.setTranslateY(17);
            }
        }
        return destroyerGroup;
    }

    @Override
    public Group drawSubmarine(boolean vertical, boolean insideGrid) {
        double shipLength = this.CELL_SIZE * 3;
        Path mainContainer = createShipOutline(shipLength);
        mainContainer.setFill(Color.web("#D9D9D9"));
        mainContainer.setStroke(Color.web("#61697A"));

        Group submarineGroup = new Group(mainContainer);
        if (vertical) {
            submarineGroup.setRotate(90);
            if (insideGrid) {
                submarineGroup.setTranslateX(-31);
                submarineGroup.setTranslateY(32);
            }
        }
        return submarineGroup;
    }

    @Override
    public Group drawAircraftCarrier(boolean vertical, boolean insideGrid) {
        double shipLength = this.CELL_SIZE * 4 + 8;
        Path mainContainer = createShipOutline(shipLength);
        mainContainer.setFill(Color.web("#D9D9D9"));
        mainContainer.setStroke(Color.web("#61697A"));

        Group aircraftGroup = new Group(mainContainer);
        if (vertical) {
            aircraftGroup.setRotate(90);
            if (insideGrid) {
                aircraftGroup.setTranslateX(-51);
                aircraftGroup.setTranslateY(50);
            }
        }
        return aircraftGroup;
    }

    private Path createShipOutline(double length) {
        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(length - 2, 2),
                new LineTo(10, 2),
                new LineTo(0, SHIP_HEIGHT / 2),
                new LineTo(10, SHIP_HEIGHT),
                new LineTo(length - 2, SHIP_HEIGHT),
                new ArcTo(14, 14, 0, length - 2, 2, false, true)
        );
        return path;
    }
}
