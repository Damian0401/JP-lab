package interfaces.services;

import models.PickupPoint;

import java.util.List;

public interface IPickupService {
    boolean addPickupPoint(PickupPoint pickupPoint) throws Exception;
    List<PickupPoint> getAllPickupPoints() throws Exception;
    PickupPoint getPickupPointById(String id) throws Exception;
    boolean removePickupPointById(String id) throws Exception;
    int getNumberOfPickupPoints(int addressId) throws Exception;
    List<String> getListOfIds() throws Exception;
    List<PickupPoint> getPickupPointsByAddressId(int addressId) throws Exception;
}
