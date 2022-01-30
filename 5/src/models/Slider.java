package models;

import java.util.List;

public class Slider {
    private List<PlaceForDish> placesForDishes;
    private boolean isMoving = false;

    public Slider(List<PlaceForDish> placesForDishes) {
        this.placesForDishes = placesForDishes;
    }

    public synchronized boolean putDish(Dish dish, int placeNumber){
        if (isMoving)
            return false;
        var place = placesForDishes.stream()
                .filter(x -> x.getPlaceNumber() == placeNumber)
                .findFirst()
                .orElse(null);
        if (place == null)
            return false;

        return place.putDish(dish);
    }

    public synchronized Dish getDish(int dishNumber, int placeNumber){
        if(isMoving)
            return null;
        var place = placesForDishes.stream()
                .filter(x -> x.getPlaceNumber() == placeNumber)
                .findFirst()
                .orElse(null);
        if (place == null)
            return null;

        return place.getDish(dishNumber);
    }

    public synchronized List<PlaceForDish> getPlacesForDishes(){
        isMoving = true;
        return placesForDishes;
    }

    public synchronized void setPlacesForDishes(List<PlaceForDish> placesForDishes){
        isMoving = false;
        this.placesForDishes = placesForDishes;
    }

}
