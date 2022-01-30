package utils;

import interfaces.IDataContext;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import models.Address;
import models.PickupPoint;
import models.Reading;

import java.io.File;
import java.util.List;

public class JsonDataContext implements IDataContext {
    private String pickupPointsFileName = "pickup-points.json";
    private String addressesFileName = "addresses.json";
    private String readingsFileName = "readings.json";
    private String resourcePath = "data/";
    private final ObjectMapper objectMapper;
    private final ObjectWriter objectWriter;

    public JsonDataContext(){
        objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
    }

    public JsonDataContext(String pickupPointsFileName, String addressesFileName, String readingsFileName){
        this();
        this.pickupPointsFileName = pickupPointsFileName;
        this.addressesFileName = addressesFileName;
        this.readingsFileName = readingsFileName;
    }

    public JsonDataContext(String pickupPointsFileName, String addressesFileName, String readingsFileName, String resourcePath){
        this(pickupPointsFileName, addressesFileName, readingsFileName);
        this.resourcePath = resourcePath;
    }

    @Override
    public List<PickupPoint> getPickupPoints() throws Exception {
        return objectMapper.readValue(new File(resourcePath + pickupPointsFileName), new TypeReference<>() {});
    }

    @Override
    public void setPickupPoints(List<PickupPoint> pickupPoints) throws Exception {
        objectWriter.writeValue(new File(resourcePath + pickupPointsFileName), pickupPoints);
    }

    @Override
    public List<Address> getAddresses() throws Exception {
        return objectMapper.readValue(new File(resourcePath + addressesFileName), new TypeReference<>() {});
    }

    @Override
    public void setAddresses(List<Address> addresses) throws Exception {
        objectWriter.writeValue(new File(resourcePath + addressesFileName), addresses);
    }

    @Override
    public List<Reading> getReadings() throws Exception {
        return objectMapper.readValue(new File(resourcePath + readingsFileName), new TypeReference<>() {});
    }

    @Override
    public void setReadings(List<Reading> readings) throws Exception {
        objectWriter.writeValue(new File(resourcePath + readingsFileName), readings);
    }

}
