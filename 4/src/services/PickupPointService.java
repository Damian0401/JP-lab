package services;

import interfaces.IDataContext;
import interfaces.services.IPickupService;
import models.PickupPoint;

import java.util.List;
import java.util.stream.Collectors;

public class PickupPointService implements IPickupService {
    private final IDataContext dataContext;

    public PickupPointService(IDataContext dataContext){
        this.dataContext = dataContext;
    }

    @Override
    public boolean addPickupPoint(PickupPoint pickupPoint) throws Exception {
        var pickupPoints = dataContext.getPickupPoints();
        if (pickupPoints.stream().anyMatch(x -> x.getId().equals(pickupPoint.getId())))
            return false;
        pickupPoints.add(pickupPoint);
        dataContext.setPickupPoints(pickupPoints);
        return true;
    }

    @Override
    public List<PickupPoint> getAllPickupPoints() throws Exception {
        return dataContext.getPickupPoints();
    }

    @Override
    public PickupPoint getPickupPointById(String id) throws Exception {
        var pickupPoints = getAllPickupPoints();
        return pickupPoints.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removePickupPointById(String id) throws Exception {
        var pickupPoints = getAllPickupPoints();
        var pickupPoint = pickupPoints.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (pickupPoint == null)
            return false;
        pickupPoints.remove(pickupPoint);
        dataContext.setPickupPoints(pickupPoints);
        return true;
    }

    @Override
    public int getNumberOfPickupPoints(int addressId) throws Exception {
        var pickupPoints = getAllPickupPoints();
        return (int) pickupPoints.stream()
                .filter(x -> x.getAddressId() == addressId)
                .count();
    }

    @Override
    public List<String> getListOfIds() throws Exception {
        var pickupPoints = getAllPickupPoints();
        return pickupPoints.stream()
                .map(PickupPoint::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<PickupPoint> getPickupPointsByAddressId(int addressId) throws Exception {
        var pickupPoints = getAllPickupPoints();
        return pickupPoints.stream()
                .filter(x -> x.getAddressId() == addressId)
                .collect(Collectors.toList());
    }
}
