package utils;

import interfaces.ITimeSeriesCollector;
import models.Pair;
import models.Reading;
import org.jfree.data.time.Day;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class TimeSeriesCollector implements ITimeSeriesCollector {
    @Override
    public List<Pair> populateData(List<Reading> readings, LocalDate startDate, LocalDate endDate){
        var pairs = new LinkedList<Pair>();
        for(var reading : readings){
            if (!reading.getDate().isBefore(startDate) && !reading.getDate().isAfter(endDate))
                pairs.add(new Pair(reading.getDate(), reading.getValue()));
        }
        pairs.sort(Comparator.comparing(Pair::getKey));
        var loopSize = pairs.size() - 1;
        Pair currentPair;
        Pair nextPair;
        double[] arrayForMissingValues;
        for(int i = 0; i < loopSize; i++){
            currentPair = pairs.get(i);
            nextPair = pairs.get(i + 1);
            var daysBetween = DAYS.between(currentPair.getKey(), nextPair.getKey());
            if (daysBetween > 1){
                arrayForMissingValues = interpolate(currentPair.getValue(), nextPair.getValue(), (int) daysBetween);
                for(int j = 1; j < arrayForMissingValues.length - 1; j++){
                    var date = currentPair.getKey().plusDays(j);
                    pairs.add(new Pair(date, arrayForMissingValues[j]));
                }
            }
        }
        pairs.sort(Comparator.comparing(Pair::getKey));
        var firstPair = pairs.getFirst();
        if (firstPair.getKey().isAfter(startDate)){
            var numberOfMissingDays = DAYS.between(startDate, firstPair.getKey());
            for(int i = 0; i < numberOfMissingDays; i++)
                pairs.add(new Pair(firstPair.getKey().minusDays(i + 1), 0));
        }
        pairs.sort(Comparator.comparing(Pair::getKey));
        var lastPair = pairs.getLast();
        if (lastPair.getKey().isBefore(endDate)){
            var numberOfMissingDays = DAYS.between(lastPair.getKey(), endDate);
            for(int i = 0; i < numberOfMissingDays; i++)
                pairs.add(new Pair(lastPair.getKey().plusDays(i + 1), lastPair.getValue()));
        }
        return pairs;
    }

    private double[] interpolate(double start, double end, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("interpolate: illegal count!");
        }
        double[] array = new double[count + 1];
        for (int i = 0; i <= count; ++ i) {
            array[i] = start + i * (end - start) / count;
        }
        return array;
    }
}
