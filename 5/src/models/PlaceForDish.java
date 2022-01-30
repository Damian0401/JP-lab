package models;

import javax.swing.*;

public class PlaceForDish {
    private final JLabel placeLabel;
    private final String defaultText;
    private final int placeNumber;
    private Dish dish;
    public PlaceForDish(JLabel placeLabel, String defaultText, int placeNumber){
        this.placeLabel = placeLabel;
        this.defaultText = defaultText;
        this.placeNumber = placeNumber;
        initialize();
    }

    public boolean putDish(Dish dish){
        if (this.dish != null)
            return false;
        this.dish = dish;
        if (dish == null)
            placeLabel.setText(defaultText);
        else
            placeLabel.setText(String.valueOf(dish.getNumber()));
        return true;
    }

    public Dish getDish(){
        var dish = this.dish;
        this.dish = null;
        placeLabel.setText(defaultText);
        return dish;
    }

    public Dish getDish(int number){
        if (dish == null || dish.getNumber() != number)
            return null;
        var dish = this.dish;
        this.dish = null;
        placeLabel.setText(defaultText);
        return dish;
    }

    private void initialize(){
        placeLabel.setText(defaultText);
    }


    public int getPlaceNumber() {
        return placeNumber;
    }
}
