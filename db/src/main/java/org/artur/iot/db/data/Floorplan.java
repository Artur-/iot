package org.artur.iot.db.data;

import javax.persistence.Embeddable;

@Embeddable
public class Floorplan {

    private double x, y;
    private String roomId;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
