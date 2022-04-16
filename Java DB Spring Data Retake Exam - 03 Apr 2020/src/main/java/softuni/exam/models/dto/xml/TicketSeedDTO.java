package softuni.exam.models.dto.xml;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDTO {

    @XmlElement(name = "serial-number")
    private String serialNumber;
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeoff;
    @XmlElement(name = "from-town")
    private TownFromDTO fromTown;
    @XmlElement(name = "to-town")
    private TownToDTO toTown;
    private PassengerEmailDTO passenger;
    private PlaneRegisterNumberDTO plane;

    @Size(min = 2)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public void setTakeoff(String takeoff) {
        this.takeoff = takeoff;
    }

    public TownFromDTO getFromTown() {
        return fromTown;
    }

    public void setFromTown(TownFromDTO fromTown) {
        this.fromTown = fromTown;
    }

    public TownToDTO getToTown() {
        return toTown;
    }

    public void setToTown(TownToDTO toTown) {
        this.toTown = toTown;
    }

    public PassengerEmailDTO getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerEmailDTO passenger) {
        this.passenger = passenger;
    }

    public PlaneRegisterNumberDTO getPlane() {
        return plane;
    }

    public void setPlane(PlaneRegisterNumberDTO plane) {
        this.plane = plane;
    }
}
