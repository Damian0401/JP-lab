package frames.utils;

import javax.swing.*;

public abstract class AMainFrame extends JFrame {
    public abstract void refreshPickupPointsTable();
    public abstract void refreshAddressesTable();
    public abstract void refreshReadingsTable();
}
