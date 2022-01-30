package services;

import interfaces.IDataContext;
import interfaces.services.IReadingService;
import models.Reading;
import utils.enums.ReadType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReadingService implements IReadingService {
    private final IDataContext dataContext;

    public ReadingService(IDataContext dataContext){
        this.dataContext = dataContext;
    }

    @Override
    public int addReading(Reading reading) throws Exception {
        var readings = getAllReadings();
        var maxId = readings.stream()
                .map(Reading::getId)
                .reduce(Integer::max)
                .orElse(0);
        reading.setId(maxId + 1);
        readings.add(reading);
        dataContext.setReadings(readings);
        return reading.getId();
    }

    @Override
    public List<Reading> getAllReadings() throws Exception {
        return dataContext.getReadings();
    }

    @Override
    public Reading getReadingById(int id) throws Exception {
        var readings = getAllReadings();
        return readings.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean removeReadingById(int id) throws Exception {
        var readings = getAllReadings();
        var reading = readings.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        if (reading == null)
            return false;
        readings.remove(reading);
        dataContext.setReadings(readings);
        return true;
    }

    @Override
    public boolean removeReadingsByPickupPointId(String id) throws Exception {
        var readings = getAllReadings();
        var readingsToRemove = readings.stream()
                .filter(x -> x.getPickupPointId().equals(id))
                .collect(Collectors.toList());
        if (readingsToRemove.size() == 0)
            return false;
        readings.removeAll(readingsToRemove);
        dataContext.setReadings(readings);
        return true;
    }

    @Override
    public List<Reading> getReadingsByPickupPointId(String id) throws Exception {
        var readings = getAllReadings();
        return readings.stream()
                .filter(x -> x.getPickupPointId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reading> getRealReadingsByPickupPointId(String id) throws Exception {
        var allReadings = getReadingsByPickupPointId(id);
        return allReadings.stream()
                .filter(x -> x.getReadType() == ReadType.Real)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reading> getExpectedReadingsByPickupPointId(String id) throws Exception {
        var allReadings = getReadingsByPickupPointId(id);
        return allReadings.stream()
                .filter(x -> x.getReadType() == ReadType.Expected)
                .collect(Collectors.toList());
    }
}
