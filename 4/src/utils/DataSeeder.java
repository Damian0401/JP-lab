package utils;

import interfaces.IDataContext;
import models.Address;
import models.PickupPoint;
import models.Reading;
import utils.enums.MediumType;
import utils.enums.ReadType;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class DataSeeder {
    public static boolean seedData(){
        File dir = new File("./data");
        if (!dir.exists()) dir.mkdirs();

        var addresses = new ArrayList<Address>();
        var pickupPoints = new ArrayList<PickupPoint>();
        var readings = new ArrayList<Reading>();

        var address1 = new Address();
        address1.setId(1);
        address1.setStreet("Cameron Barri");
        address1.setPostCode("24-156");
        address1.setHouseNumber(4);

        var address2 = new Address();
        address2.setId(2);
        address2.setStreet("Clos Ceri");
        address2.setPostCode("73-532");
        address2.setHouseNumber(214);

        addresses.add(address1);
        addresses.add(address2);

        var pickupPoint1 = new PickupPoint();
        pickupPoint1.setId("gs2dw1");
        pickupPoint1.setAddressId(1);
        pickupPoint1.setMediumType(MediumType.Gas);

        var pickupPoint2 = new PickupPoint();
        pickupPoint2.setId("gs3t3");
        pickupPoint2.setAddressId(1);
        pickupPoint2.setMediumType(MediumType.Water);


        var pickupPoint3 = new PickupPoint();
        pickupPoint3.setId("gs6t3");
        pickupPoint3.setAddressId(2);
        pickupPoint3.setMediumType(MediumType.Water);

        var pickupPoint4 = new PickupPoint();
        pickupPoint4.setId("gs8t1");
        pickupPoint4.setAddressId(2);
        pickupPoint4.setMediumType(MediumType.Electricity);

        var pickupPoint5 = new PickupPoint();
        pickupPoint5.setId("gp4k1");
        pickupPoint5.setAddressId(2);
        pickupPoint5.setMediumType(MediumType.Electricity);

        pickupPoints.add(pickupPoint1);
        pickupPoints.add(pickupPoint2);
        pickupPoints.add(pickupPoint3);
        pickupPoints.add(pickupPoint4);
        pickupPoints.add(pickupPoint5);

        int id = 1;
        Reading randomReading;
        var random = new Random();
        var startDate = LocalDate.parse("2020-01-01");
        for(int i = 0; i < 10; i++){
            randomReading = new Reading();
            randomReading.setId(id++);
            randomReading.setValue(random.nextInt(20));
            randomReading.setDate(startDate.plusDays(random.nextInt(364)));
            randomReading.setPickupPointId("gs2dw1");
            randomReading.setReadType(random.nextBoolean() ? ReadType.Real : ReadType.Expected);
            readings.add(randomReading);
        }
        for(int i = 0; i < 20; i++){
            randomReading = new Reading();
            randomReading.setId(id++);
            randomReading.setValue(random.nextInt(40));
            randomReading.setDate(startDate.plusDays(random.nextInt(364)));
            randomReading.setPickupPointId("gs3t3");
            randomReading.setReadType(random.nextBoolean() ? ReadType.Real : ReadType.Expected);
            readings.add(randomReading);
        }
        for(int i = 0; i < 20; i++){
            randomReading = new Reading();
            randomReading.setId(id++);
            randomReading.setValue(random.nextInt(60));
            randomReading.setDate(startDate.plusDays(random.nextInt(364)));
            randomReading.setPickupPointId("gs6t3");
            randomReading.setReadType(random.nextBoolean() ? ReadType.Real : ReadType.Expected);
            readings.add(randomReading);
        }
        for(int i = 0; i < 20; i++){
            randomReading = new Reading();
            randomReading.setId(id++);
            randomReading.setValue(random.nextInt(80));
            randomReading.setDate(startDate.plusDays(random.nextInt(364)));
            randomReading.setPickupPointId("gs8t1");
            randomReading.setReadType(random.nextBoolean() ? ReadType.Real : ReadType.Expected);
            readings.add(randomReading);
        }

        for(int i = 0; i < 20; i++){
            randomReading = new Reading();
            randomReading.setId(id++);
            randomReading.setValue(random.nextInt(100));
            randomReading.setDate(startDate.plusDays(random.nextInt(364)));
            randomReading.setPickupPointId("gp4k1");
            randomReading.setReadType(random.nextBoolean() ? ReadType.Real : ReadType.Expected);
            readings.add(randomReading);
        }

        IDataContext dataContext = new JsonDataContext();

        try {
            dataContext.setAddresses(addresses);
            dataContext.setPickupPoints(pickupPoints);
            dataContext.setReadings(readings);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
