import frames.RestaurantPanelCreator;
import frames.SettingsPanelCreator;
import interfaces.IRestaurantCreator;
import interfaces.ISettingsCreator;
import models.TimeSettings;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        // set-up
        var timeSettings = new TimeSettings(1000, 5000, 3000);
        IRestaurantCreator restaurantCreator = new RestaurantPanelCreator(timeSettings, 5);
        var runnableAndFinishableInstances = restaurantCreator.getRunnableAndFinishableInstances();
        ISettingsCreator settingsCreator = new SettingsPanelCreator(timeSettings, runnableAndFinishableInstances);

        // crate frame
        var frame = createFrame(restaurantCreator, settingsCreator, "Sushi bar");
        frame.setVisible(true);
    }

    public static JFrame createFrame(IRestaurantCreator restaurantCreator, ISettingsCreator settingsCreator, String title){
        // create frame
        JFrame frame = new JFrame(title);
        var mainPanel = new JPanel(new BorderLayout());

        // create restaurantPanel
        var restaurantPanel = restaurantCreator.getRestaurantComponent();

        // create settingsPanel
        var settingsPanel = settingsCreator.getSettingsComponent();

        // set-up
        mainPanel.add(restaurantPanel, BorderLayout.CENTER);
        mainPanel.add(settingsPanel, BorderLayout.LINE_END);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setSize(650, 350);
        frame.add(mainPanel);

        // return frame
        return frame;
    }
}
