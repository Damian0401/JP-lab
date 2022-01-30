package threads;

import models.Dish;
import models.PlaceForDish;
import models.Slider;
import models.TimeSettings;
import threads.utils.RunnableAndFinishable;

import java.util.List;

public class Mover extends RunnableAndFinishable {
    private final Slider slider;
    private final TimeSettings timeSettings;
    public Mover(Slider slider, TimeSettings timeSettings) {
        this.slider = slider;
        this.timeSettings = timeSettings;
    }

    @Override
    public void run() {
        while (!isFinished){
            var places = slider.getPlacesForDishes();
            move(places);
            slider.setPlacesForDishes(places);
            delay(timeSettings.getMoverDelay());
        }
    }

    private void move(List<PlaceForDish> placesForDishes){
        var iterator = placesForDishes.iterator();
        PlaceForDish placeForDish = iterator.next();
        Dish currentDish;
        Dish previousDish = placeForDish.getDish();
        while (iterator.hasNext()){
            placeForDish = iterator.next();
            currentDish = placeForDish.getDish();
            placeForDish.putDish(previousDish);
            previousDish = currentDish;
        }
        placesForDishes.get(0).putDish(previousDish);
    }
}
