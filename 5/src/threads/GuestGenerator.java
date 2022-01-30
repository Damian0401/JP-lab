package threads;

import enums.PlaceForGuestStatus;
import models.PlaceForGuest;
import models.Slider;
import models.TimeSettings;
import threads.utils.RunnableAndFinishable;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GuestGenerator extends RunnableAndFinishable {
    private final TimeSettings timeSettings;
    private final List<PlaceForGuest> placesForGuests;
    private final Slider slider;
    private final int numberOfDishes;
    private final Random random;
    private final List<Guest> guests;
    private int howManyLoops;

    public GuestGenerator(TimeSettings timeSettings, Slider slider, List<PlaceForGuest> placesForGuests, int numberOfDishes) {
        this.timeSettings = timeSettings;
        this.placesForGuests = placesForGuests;
        this.numberOfDishes = numberOfDishes;
        this.slider = slider;
        this.guests = new LinkedList<>();
        this.random = new Random();
        this.howManyLoops = 0;
    }

    @Override
    public void run() {
        while (!isFinished){
            delay(timeSettings.getGuestAppearingDelay());
            var guest = generateGuest();
            if (guest == null)
                continue;
            new Thread(guest).start();
            guests.add(guest);
            cleanListOfGuest();
        }
    }

    @Override
    public void finish(){
        guests.forEach(RunnableAndFinishable::finish);
        super.finish();
    }

    private void cleanListOfGuest(){
        howManyLoops++;
        if (howManyLoops > 10){
            var guestsToRemove = guests.stream()
                    .filter(RunnableAndFinishable::isFinished)
                    .collect(Collectors.toList());
            guests.removeAll(guestsToRemove);
            howManyLoops = 0;
        }

    }

    private Guest generateGuest(){
        var freePlaces = placesForGuests.stream()
                .filter(x -> x.getStatus() == PlaceForGuestStatus.Empty)
                .collect(Collectors.toList());
        if (freePlaces.size() == 0)
            return null;
        var index = random.nextInt(freePlaces.size());
        var place = freePlaces.get(index);
        var numberOfDish = random.nextInt(numberOfDishes) + 1;
        return new Guest(slider, numberOfDish, place);
    }
}
