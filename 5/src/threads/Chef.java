package threads;

import models.Dish;
import models.Slider;
import models.TimeSettings;
import threads.utils.RunnableAndFinishable;

import javax.swing.*;
import java.util.Random;

public class Chef extends RunnableAndFinishable {
    private final JLabel chefLabel;
    private final Slider slider;
    private final int numberOfPlaceToServeDish;
    private final TimeSettings timeSettings;
    private final int numberOfDishes;
    private Random random;

    public Chef(JLabel chefLabel, Slider slider, TimeSettings timeSettings,
                int numberOfPlaceToServeDish, int numberOfDishes) {
        this.chefLabel = chefLabel;
        this.numberOfPlaceToServeDish = numberOfPlaceToServeDish;
        this.slider = slider;
        this.timeSettings = timeSettings;
        this.numberOfDishes = numberOfDishes;
        initialize();
    }

    private void initialize(){
        chefLabel.setText("K");
        random = new Random();
    }

    @Override
    public void run() {
        while (!isFinished){
            chefLabel.setText("K");
            delay(timeSettings.getChefDelay());
            chefLabel.setText("K??");
            delay(timeSettings.getChefDelay());
            var dishNumber = random.nextInt(numberOfDishes) + 1;
            chefLabel.setText("K" + dishNumber);
            boolean isAdded = false;
            while (!isAdded){
		if (isFinished)
			continue;
                delay(100);
                isAdded = slider.putDish(new Dish(dishNumber), numberOfPlaceToServeDish);
            }
        }
    }
}
