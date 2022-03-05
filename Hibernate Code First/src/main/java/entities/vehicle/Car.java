package entities.vehicle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
@DiscriminatorValue("car")
public class Car extends Vehicle{
    private static final String CAR_TYPE = "Car";

    private int doorCount;

    public Car(int doorCount) {
        super(CAR_TYPE, 1000);
        this.doorCount = doorCount;
    }

    public int getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(int doorCount) {
        this.doorCount = doorCount;
    }
}
