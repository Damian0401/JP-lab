package interfaces;

import models.PlaceForGuest;
import threads.Chef;
import threads.Mover;
import threads.utils.RunnableAndFinishable;

import javax.swing.*;
import java.util.List;

public interface IRestaurantCreator {
    JComponent getRestaurantComponent();
    List<RunnableAndFinishable> getRunnableAndFinishableInstances();
}
