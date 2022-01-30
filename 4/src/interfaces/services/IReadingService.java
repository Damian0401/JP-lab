package interfaces.services;

import models.Reading;

import java.util.List;

public interface IReadingService {
    int addReading(Reading reading) throws Exception;
    List<Reading> getAllReadings() throws Exception;
    Reading getReadingById(int id) throws Exception;
    boolean removeReadingById(int id) throws Exception;
    boolean removeReadingsByPickupPointId(String id) throws Exception;
    List<Reading> getReadingsByPickupPointId(String id) throws Exception;
    List<Reading> getRealReadingsByPickupPointId(String id) throws Exception;
    List<Reading> getExpectedReadingsByPickupPointId(String id) throws Exception;
}
