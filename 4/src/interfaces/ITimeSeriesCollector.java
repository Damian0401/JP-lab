package interfaces;

import models.Pair;
import models.Reading;

import java.time.LocalDate;
import java.util.List;

public interface ITimeSeriesCollector {
    List<Pair> populateData(List<Reading> readings, LocalDate startDate, LocalDate endDate);
}
