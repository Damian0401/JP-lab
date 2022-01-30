package interfaces;

import models.Address;
import models.PickupPoint;
import models.Reading;

import java.util.List;

public interface IDataContext {
    // Pickup
    List<PickupPoint> getPickupPoints() throws Exception;
    void setPickupPoints(List<PickupPoint> pickupPoints) throws Exception;

    // Address
    List<Address> getAddresses() throws Exception;
    void setAddresses(List<Address> addresses) throws Exception;

    // Reading
    List<Reading> getReadings() throws Exception;
    void setReadings(List<Reading> readings) throws Exception;
}
