package app.model.entity;

import app.model.entity.enums.TypeR;

import java.util.Objects;

public class Room {
    private String codRoom;
    private int beds;
    private int windows;
    private TypeR typeR;
    private int price;


    public Room(String codRoom, int beds, int windows, TypeR typeR,int price) {
        this.codRoom = codRoom;
        this.beds = beds;
        this.windows = windows;
        this.typeR= typeR;
        this.price = price;
    }


    public Room() {
    }


    public String getCodRoom() {
        return codRoom;
    }

    public void setCodRoom(String codRoom) {
        this.codRoom = codRoom;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getWindows() {
        return windows;
    }

    public void setWindows(int windows) {
        this.windows = windows;
    }
    public TypeR getTypeR() {
        return typeR;
    }

    public void setTypeR(TypeR typeR) {
        this.typeR = typeR;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }




    @Override
    public boolean equals(Object obj) {
        boolean isEquals;
        if (this == obj) {
            isEquals = true;
        } else if (obj == null || getClass() != obj.getClass()) {
            isEquals = false;
        } else {
            Room room = (Room) obj;
            return Objects.equals(codRoom, room.codRoom);
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codRoom, beds, windows,typeR,price);
    }

    @Override
    public String toString() {
        return "Room[" +
                "codRoom=  " + codRoom +
                ", bed = " + beds +
                ", windows = " + windows +
                ", typeR ="  + typeR +
                ", price = " + price +
                ']';
    }
}
