package threads;

import models.PlaceForGuest;
import models.Slider;
import threads.utils.RunnableAndFinishable;

public class Guest extends RunnableAndFinishable {
    private final Slider slider;
    private final int dishNumber;
    private final PlaceForGuest placeForGuest;

    public Guest(Slider slider, int dishNumber, PlaceForGuest placeForGuest) {
        this.slider = slider;
        this.dishNumber = dishNumber;
        this.placeForGuest = placeForGuest;
        placeForGuest.takePlace(dishNumber);
    }

    @Override
    public void run() {
        while (!isFinished){
            delay(100);
            var dish = slider.getDish(dishNumber, placeForGuest.getNumberOfPlace());
            if (dish != null){
                placeForGuest.freePlace();
                finish();
            }
        }
    }
}
