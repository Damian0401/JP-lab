package frames;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import interfaces.IRestaurantCreator;
import models.PlaceForGuest;
import models.Slider;
import models.TimeSettings;
import threads.Chef;
import models.PlaceForDish;
import threads.GuestGenerator;
import threads.Mover;
import threads.utils.RunnableAndFinishable;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Locale;

public class RestaurantPanelCreator implements IRestaurantCreator {
    private JPanel mainPanel;
    private JPanel barPanel;
    private JLabel chefLabel1;
    private JLabel chefLabel2;
    private JLabel chefLabel3;
    private JLabel chefLabel4;
    private JPanel sliderPanel;
    private JLabel sliderLabel1;
    private JLabel sliderLabel2;
    private JLabel sliderLabel3;
    private JLabel sliderLabel4;
    private JLabel sliderLabel5;
    private JLabel sliderLabel6;
    private JLabel sliderLabel7;
    private JLabel sliderLabel8;
    private JLabel sliderLabel9;
    private JLabel sliderLabel10;
    private JLabel sliderLabel11;
    private JLabel sliderLabel12;
    private JLabel sliderLabel13;
    private JLabel sliderLabel14;
    private JLabel sliderLabel15;
    private JLabel sliderLabel16;
    private JLabel sliderLabel17;
    private JLabel sliderLabel18;
    private JLabel sliderLabel19;
    private JLabel sliderLabel20;
    private JLabel sliderLabel21;
    private JLabel sliderLabel22;
    private JLabel sliderLabel23;
    private JLabel sliderLabel24;
    private JLabel sliderLabel25;
    private JLabel sliderLabel26;
    private JLabel sliderLabel27;
    private JLabel sliderLabel28;
    private JLabel sliderLabel29;
    private JLabel sliderLabel30;
    private JLabel sliderLabel31;
    private JLabel sliderLabel32;
    private JLabel guestLabel1;
    private JLabel guestLabel2;
    private JLabel guestLabel3;
    private JLabel guestLabel4;
    private JLabel guestLabel5;
    private JLabel guestLabel6;
    private JLabel guestLabel7;
    private JLabel guestLabel8;
    private JLabel guestLabel9;
    private JLabel guestLabel10;
    private JLabel guestLabel11;
    private JLabel guestLabel12;
    private JLabel guestLabel13;
    private JLabel guestLabel14;
    private JLabel guestLabel15;
    private JLabel guestLabel16;
    private List<Chef> chefs;
    private Slider slider;
    private Mover mover;
    private GuestGenerator guestGenerator;


    public RestaurantPanelCreator(TimeSettings timeSettings, int numberOfDishes) {
        initializePlacesForDishes();
        initializePlacesForGuests(timeSettings, numberOfDishes);
        initializeChefs(timeSettings, numberOfDishes);
        initializeMover(timeSettings);
    }

    private void initializeChefs(TimeSettings timeSettings, int numberOfDishes) {
        var list = new ArrayList<Chef>();
        list.add(new Chef(chefLabel1, slider, timeSettings, 1, numberOfDishes));
        list.add(new Chef(chefLabel2, slider, timeSettings, 7, numberOfDishes));
        list.add(new Chef(chefLabel3, slider, timeSettings, 11, numberOfDishes));
        list.add(new Chef(chefLabel4, slider, timeSettings, 17, numberOfDishes));
        chefs = list;
    }

    private void initializePlacesForDishes() {
        var listForDishes = new ArrayList<PlaceForDish>(20);
        listForDishes.add(new PlaceForDish(sliderLabel1, "/", 1));
        listForDishes.add(new PlaceForDish(sliderLabel2, "x", 2));
        sliderLabel3.setText(">");
        listForDishes.add(new PlaceForDish(sliderLabel4, "x", 3));
        sliderLabel5.setText(">");
        listForDishes.add(new PlaceForDish(sliderLabel6, "x", 4));
        sliderLabel7.setText(">");
        listForDishes.add(new PlaceForDish(sliderLabel8, "x", 5));
        sliderLabel9.setText(">");
        listForDishes.add(new PlaceForDish(sliderLabel10, "x", 6));
        listForDishes.add(new PlaceForDish(sliderLabel11, "\\", 7));
        listForDishes.add(new PlaceForDish(sliderLabel12, "x", 8));
        sliderLabel13.setText("v");
        listForDishes.add(new PlaceForDish(sliderLabel14, "x", 9));
        sliderLabel15.setText("v");
        listForDishes.add(new PlaceForDish(sliderLabel16, "x", 10));
        listForDishes.add(new PlaceForDish(sliderLabel17, "/", 11));
        listForDishes.add(new PlaceForDish(sliderLabel18, "x", 12));
        sliderLabel19.setText("<");
        listForDishes.add(new PlaceForDish(sliderLabel20, "x", 13));
        sliderLabel21.setText("<");
        listForDishes.add(new PlaceForDish(sliderLabel22, "x", 14));
        sliderLabel23.setText("<");
        listForDishes.add(new PlaceForDish(sliderLabel24, "x", 15));
        sliderLabel25.setText("<");
        listForDishes.add(new PlaceForDish(sliderLabel26, "x", 16));
        listForDishes.add(new PlaceForDish(sliderLabel27, "\\", 17));
        listForDishes.add(new PlaceForDish(sliderLabel28, "x", 18));
        sliderLabel29.setText("^");
        listForDishes.add(new PlaceForDish(sliderLabel30, "x", 19));
        sliderLabel31.setText("^");
        listForDishes.add(new PlaceForDish(sliderLabel32, "x", 20));
        slider = new Slider(listForDishes);
    }

    private void initializePlacesForGuests(TimeSettings timeSettings, int numberOfDishes) {
        var placesForGuests = new ArrayList<PlaceForGuest>(20);
        placesForGuests.add(new PlaceForGuest(guestLabel1, 2));
        placesForGuests.add(new PlaceForGuest(guestLabel2, 3));
        placesForGuests.add(new PlaceForGuest(guestLabel3, 4));
        placesForGuests.add(new PlaceForGuest(guestLabel4, 5));
        placesForGuests.add(new PlaceForGuest(guestLabel5, 6));
        placesForGuests.add(new PlaceForGuest(guestLabel6, 8));
        placesForGuests.add(new PlaceForGuest(guestLabel7, 9));
        placesForGuests.add(new PlaceForGuest(guestLabel8, 10));
        placesForGuests.add(new PlaceForGuest(guestLabel9, 12));
        placesForGuests.add(new PlaceForGuest(guestLabel10, 13));
        placesForGuests.add(new PlaceForGuest(guestLabel11, 14));
        placesForGuests.add(new PlaceForGuest(guestLabel12, 15));
        placesForGuests.add(new PlaceForGuest(guestLabel13, 16));
        placesForGuests.add(new PlaceForGuest(guestLabel14, 18));
        placesForGuests.add(new PlaceForGuest(guestLabel15, 19));
        placesForGuests.add(new PlaceForGuest(guestLabel16, 20));
        guestGenerator = new GuestGenerator(timeSettings, slider, placesForGuests, numberOfDishes);
    }

    private void initializeMover(TimeSettings timeSettings) {
        mover = new Mover(slider, timeSettings);
    }

    @Override
    public JComponent getRestaurantComponent() {
        return $$$getRootComponent$$$();
    }

    @Override
    public List<RunnableAndFinishable> getRunnableAndFinishableInstances() {
        var list = new ArrayList<RunnableAndFinishable>(6);
        list.addAll(chefs);
        list.add(mover);
        list.add(guestGenerator);
        return list;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new GridLayoutManager(9, 13, new Insets(0, 0, 0, 0), -1, -1));
        sliderPanel.setBackground(new Color(-1776227));
        mainPanel.add(sliderPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sliderPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, -1, -1, sliderPanel.getFont()), new Color(-4473925)));
        sliderLabel1 = new JLabel();
        sliderLabel1.setText("Label");
        sliderPanel.add(sliderLabel1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel11 = new JLabel();
        sliderLabel11.setText("Label");
        sliderPanel.add(sliderLabel11, new GridConstraints(1, 11, 1, 1, GridConstraints.ANCHOR_SOUTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel2 = new JLabel();
        sliderLabel2.setText("Label");
        sliderPanel.add(sliderLabel2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel10 = new JLabel();
        sliderLabel10.setText("Label");
        sliderPanel.add(sliderLabel10, new GridConstraints(1, 10, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel12 = new JLabel();
        sliderLabel12.setText("Label");
        sliderPanel.add(sliderLabel12, new GridConstraints(2, 11, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel14 = new JLabel();
        sliderLabel14.setText("Label");
        sliderPanel.add(sliderLabel14, new GridConstraints(4, 11, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel13 = new JLabel();
        sliderLabel13.setText("Label");
        sliderPanel.add(sliderLabel13, new GridConstraints(3, 11, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel15 = new JLabel();
        sliderLabel15.setText("Label");
        sliderPanel.add(sliderLabel15, new GridConstraints(5, 11, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel32 = new JLabel();
        sliderLabel32.setText("Label");
        sliderPanel.add(sliderLabel32, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel31 = new JLabel();
        sliderLabel31.setText("Label");
        sliderPanel.add(sliderLabel31, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel30 = new JLabel();
        sliderLabel30.setText("Label");
        sliderPanel.add(sliderLabel30, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel16 = new JLabel();
        sliderLabel16.setText("Label");
        sliderPanel.add(sliderLabel16, new GridConstraints(6, 11, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel29 = new JLabel();
        sliderLabel29.setText("Label");
        sliderPanel.add(sliderLabel29, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel28 = new JLabel();
        sliderLabel28.setText("Label");
        sliderPanel.add(sliderLabel28, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        barPanel = new JPanel();
        barPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        barPanel.setBackground(new Color(-4608452));
        sliderPanel.add(barPanel, new GridConstraints(2, 2, 5, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chefLabel2 = new JLabel();
        chefLabel2.setText("Label");
        barPanel.add(chefLabel2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chefLabel3 = new JLabel();
        chefLabel3.setText("Label");
        barPanel.add(chefLabel3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        chefLabel4 = new JLabel();
        chefLabel4.setText("Label");
        barPanel.add(chefLabel4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        barPanel.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        barPanel.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        chefLabel1 = new JLabel();
        chefLabel1.setText("Label");
        barPanel.add(chefLabel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel17 = new JLabel();
        sliderLabel17.setText("Label");
        sliderPanel.add(sliderLabel17, new GridConstraints(7, 11, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel27 = new JLabel();
        sliderLabel27.setText("Label");
        sliderPanel.add(sliderLabel27, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_NORTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel26 = new JLabel();
        sliderLabel26.setText("Label");
        sliderPanel.add(sliderLabel26, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel18 = new JLabel();
        sliderLabel18.setText("Label");
        sliderPanel.add(sliderLabel18, new GridConstraints(7, 10, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel25 = new JLabel();
        sliderLabel25.setText("Label");
        sliderPanel.add(sliderLabel25, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel24 = new JLabel();
        sliderLabel24.setText("Label");
        sliderPanel.add(sliderLabel24, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel23 = new JLabel();
        sliderLabel23.setText("Label");
        sliderPanel.add(sliderLabel23, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel22 = new JLabel();
        sliderLabel22.setText("Label");
        sliderPanel.add(sliderLabel22, new GridConstraints(7, 6, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel21 = new JLabel();
        sliderLabel21.setText("Label");
        sliderPanel.add(sliderLabel21, new GridConstraints(7, 7, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel19 = new JLabel();
        sliderLabel19.setText("Label");
        sliderPanel.add(sliderLabel19, new GridConstraints(7, 9, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel20 = new JLabel();
        sliderLabel20.setText("Label");
        sliderPanel.add(sliderLabel20, new GridConstraints(7, 8, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel9 = new JLabel();
        sliderLabel9.setText("Label");
        sliderPanel.add(sliderLabel9, new GridConstraints(1, 9, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel8 = new JLabel();
        sliderLabel8.setText("Label");
        sliderPanel.add(sliderLabel8, new GridConstraints(1, 8, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel7 = new JLabel();
        sliderLabel7.setText("Label");
        sliderPanel.add(sliderLabel7, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel6 = new JLabel();
        sliderLabel6.setText("Label");
        sliderPanel.add(sliderLabel6, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel5 = new JLabel();
        sliderLabel5.setText("Label");
        sliderPanel.add(sliderLabel5, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel3 = new JLabel();
        sliderLabel3.setText("Label");
        sliderPanel.add(sliderLabel3, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLabel4 = new JLabel();
        sliderLabel4.setText("Label");
        sliderPanel.add(sliderLabel4, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 9, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-1904612));
        sliderPanel.add(panel1, new GridConstraints(0, 2, 1, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        guestLabel1 = new JLabel();
        guestLabel1.setText("Label");
        panel1.add(guestLabel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel5 = new JLabel();
        guestLabel5.setText("Label");
        panel1.add(guestLabel5, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel2 = new JLabel();
        guestLabel2.setText("Label");
        panel1.add(guestLabel2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel3 = new JLabel();
        guestLabel3.setText("Label");
        panel1.add(guestLabel3, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel4 = new JLabel();
        guestLabel4.setText("Label");
        panel1.add(guestLabel4, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel1.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-1904612));
        sliderPanel.add(panel2, new GridConstraints(2, 12, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        guestLabel6 = new JLabel();
        guestLabel6.setText("Label");
        panel2.add(guestLabel6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel8 = new JLabel();
        guestLabel8.setText("Label");
        panel2.add(guestLabel8, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel7 = new JLabel();
        guestLabel7.setText("Label");
        panel2.add(guestLabel7, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel2.add(spacer7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel2.add(spacer8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 9, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-1904612));
        sliderPanel.add(panel3, new GridConstraints(8, 2, 1, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        guestLabel13 = new JLabel();
        guestLabel13.setText("Label");
        panel3.add(guestLabel13, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel9 = new JLabel();
        guestLabel9.setText("Label");
        panel3.add(guestLabel9, new GridConstraints(0, 8, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel12 = new JLabel();
        guestLabel12.setText("Label");
        panel3.add(guestLabel12, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel10 = new JLabel();
        guestLabel10.setText("Label");
        panel3.add(guestLabel10, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel11 = new JLabel();
        guestLabel11.setText("Label");
        panel3.add(guestLabel11, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        panel3.add(spacer9, new GridConstraints(0, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        panel3.add(spacer10, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        panel3.add(spacer11, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        panel3.add(spacer12, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-1904612));
        sliderPanel.add(panel4, new GridConstraints(2, 0, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        guestLabel16 = new JLabel();
        guestLabel16.setText("Label");
        panel4.add(guestLabel16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel14 = new JLabel();
        guestLabel14.setText("Label");
        panel4.add(guestLabel14, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        guestLabel15 = new JLabel();
        guestLabel15.setText("Label");
        panel4.add(guestLabel15, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        panel4.add(spacer13, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        panel4.add(spacer14, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
