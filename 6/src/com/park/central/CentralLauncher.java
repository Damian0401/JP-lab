package com.park.central;

import com.park.common.data.DataContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CentralLauncher {
    public static void main(String[] args) {

        seedData();

        run();
    }

    public static void run(){
        var dataContext = new DataContext();
        var centralService = new CentralService(dataContext);
        var centralGUI = new CentralGUI(centralService);
        centralService.addListener(centralGUI);
        centralGUI.run();
    }

    private static void seedData(){
        String directoryPath = "data/";
        String attractionsFileName = "attractions.json";
        String ticketsFileName = "tickets.json";

        File directory = new File(directoryPath);
        if (!directory.exists()){
            boolean isCreated = directory.mkdir();
            if (!isCreated){
                return;
            }
            File attractionFile = new File(directoryPath + attractionsFileName);
            File ticketsFile = new File(directoryPath + ticketsFileName);
            try{
                FileWriter attractionsFileWriter = new FileWriter(attractionFile.getAbsoluteFile());
                BufferedWriter ticketsBuffetedWriter = new BufferedWriter(attractionsFileWriter);
                ticketsBuffetedWriter.write("[]");
                ticketsBuffetedWriter.close();
                FileWriter ticketsFileWriter = new FileWriter(ticketsFile.getAbsoluteFile());
                BufferedWriter ticketsBufferedWriter = new BufferedWriter(ticketsFileWriter);
                ticketsBufferedWriter.write("[]");
                ticketsBufferedWriter.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
