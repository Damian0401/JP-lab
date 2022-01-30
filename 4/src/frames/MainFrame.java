package frames;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import frames.utils.AMainFrame;
import interfaces.services.IAddressService;
import interfaces.services.IPickupService;
import interfaces.services.IReadingService;
import utils.DataSeeder;
import utils.enums.ChartType;
import utils.table.AddressesTableModel;
import utils.table.PickupPointTableModel;
import utils.table.ReadingsTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends AMainFrame {
    private JTable pickupPointsTable;
    private JTable addressesTable;
    private JPanel mainPanel;
    private JLabel addressesLabel;
    private JLabel pickupPointsLabel;
    private JScrollPane pickupPointsScrollPane;
    private JScrollPane addressesScrollPane;
    private JButton addPickupPointButton;
    private JButton removePickupPointButton;
    private JButton generateButton;
    private JButton addReadingButton;
    private JTable readingsTable;
    private JLabel readingsLabel;
    private JScrollPane readingsScrollPane;
    private JButton seedDataButton;
    private JButton removeReadingButton;
    private JButton exitButton;
    private final IAddressService addressService;
    private final IPickupService pickupService;
    private final IReadingService readingService;
    private final AMainFrame mainFrame;

    public MainFrame(IAddressService addressService, IPickupService pickupService, IReadingService readingService) {
        this.addressService = addressService;
        this.pickupService = pickupService;
        this.readingService = readingService;
        this.mainFrame = this;
        initializeFrame();
        initializeComponents();
    }

    private void initializeFrame() {
        setTitle("MainFrame");
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 500);
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(232, 189, 9, 166));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        addressesTable.getTableHeader().setReorderingAllowed(false);
        readingsTable.getTableHeader().setReorderingAllowed(false);
        pickupPointsTable.getTableHeader().setReorderingAllowed(false);
        refreshAddressesTable();
        refreshPickupPointsTable();
        refreshReadingsTable();
        addPickupPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPickupPointFrame(addressService, pickupService, mainFrame);
            }
        });
        removePickupPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var id = JOptionPane.showInputDialog("Enter identifier of the pickupPoint that you want to delete:");
                if (id == null || id.isEmpty() || id.isBlank())
                    return;
                tryDeletePickupPoint(id);
            }
        });
        seedDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var isDataSeeded = DataSeeder.seedData();
                if (isDataSeeded) {
                    refreshAddressesTable();
                    refreshReadingsTable();
                    refreshPickupPointsTable();
                    JOptionPane.showMessageDialog(null, "Data seeded successfully");
                }
            }
        });
        addReadingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddReadingFrame(readingService, pickupService, mainFrame);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        removeReadingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var id = JOptionPane.showInputDialog("Enter identifier of the reading that you want to delete:");
                if (id == null || id.isEmpty() || id.isBlank())
                    return;
                tryDeleteReading(id);
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var selectedOption = JOptionPane.showOptionDialog(null, "Select type of the chart",
                        "Create chart", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        ChartType.values(), ChartType.Differences);
                switch (selectedOption) {
                    case 0:
                        new DifferenceChartGenerator(pickupService, readingService);
                        break;
                    case 1:
                        new SummaryChartGenerator(pickupService, readingService, addressService);
                        break;
                }
            }
        });
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
        mainPanel.setLayout(new GridLayoutManager(8, 5, new Insets(0, 0, 0, 0), -1, -1));
        addressesScrollPane = new JScrollPane();
        mainPanel.add(addressesScrollPane, new GridConstraints(3, 2, 2, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addressesTable = new JTable();
        addressesScrollPane.setViewportView(addressesTable);
        generateButton = new JButton();
        generateButton.setText("Generate chart");
        mainPanel.add(generateButton, new GridConstraints(7, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        readingsScrollPane = new JScrollPane();
        mainPanel.add(readingsScrollPane, new GridConstraints(1, 0, 6, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        readingsTable = new JTable();
        readingsScrollPane.setViewportView(readingsTable);
        pickupPointsScrollPane = new JScrollPane();
        mainPanel.add(pickupPointsScrollPane, new GridConstraints(1, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pickupPointsTable = new JTable();
        pickupPointsScrollPane.setViewportView(pickupPointsTable);
        readingsLabel = new JLabel();
        readingsLabel.setText("Readings:");
        mainPanel.add(readingsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addressesLabel = new JLabel();
        addressesLabel.setText("Addresses:");
        mainPanel.add(addressesLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pickupPointsLabel = new JLabel();
        pickupPointsLabel.setText("PickupPoints:");
        mainPanel.add(pickupPointsLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addReadingButton = new JButton();
        addReadingButton.setText("Add reading");
        mainPanel.add(addReadingButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removePickupPointButton = new JButton();
        removePickupPointButton.setText("Remove pickup");
        mainPanel.add(removePickupPointButton, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addPickupPointButton = new JButton();
        addPickupPointButton.setText("Add pickup");
        mainPanel.add(addPickupPointButton, new GridConstraints(5, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        seedDataButton = new JButton();
        seedDataButton.setText("Seed data");
        mainPanel.add(seedDataButton, new GridConstraints(6, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removeReadingButton = new JButton();
        removeReadingButton.setText("Remove reading");
        mainPanel.add(removeReadingButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        mainPanel.add(exitButton, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    @Override
    public void refreshAddressesTable() {
        try {
            var addresses = addressService.getAllAddresses();
            var addressesTableModel = new AddressesTableModel(addresses);
            addressesTable.setModel(addressesTableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load addresses");
        }
    }

    @Override
    public void refreshReadingsTable() {
        try {
            var readings = readingService.getAllReadings();
            var readingsTableModel = new ReadingsTableModel(readings);
            readingsTable.setModel(readingsTableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load readings");
        }
    }

    @Override
    public void refreshPickupPointsTable() {
        try {
            var pickupPoints = pickupService.getAllPickupPoints();
            var pickupPointsTableModel = new PickupPointTableModel(pickupPoints);
            pickupPointsTable.setModel(pickupPointsTableModel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load pickupPoints");
        }
    }

    private void tryDeletePickupPoint(String id) {
        try {
            var pickupPoint = pickupService.getPickupPointById(id);
            if (pickupPoint == null) {
                JOptionPane.showMessageDialog(this, "PickupPoint not found");
                return;
            }
            var isDeleted = pickupService.removePickupPointById(id);
            if (isDeleted) {
                refreshPickupPointsTable();
                if (pickupService.getNumberOfPickupPoints(pickupPoint.getAddressId()) == 0) {
                    addressService.removeAddressById(pickupPoint.getAddressId());
                    refreshAddressesTable();
                }
                if (readingService.removeReadingsByPickupPointId(id))
                    refreshReadingsTable();
                JOptionPane.showMessageDialog(this, "PickupPoint with id '"
                        + id + "' deleted successfully");
                return;
            }
            JOptionPane.showMessageDialog(this, "Unable to delete pickupPoint");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error occurred");
        }
    }

    private void tryDeleteReading(String id) {
        try {
            var parsedId = Integer.parseInt(id);
            var isDeleted = readingService.removeReadingById(parsedId);
            var reading = readingService.getReadingById(parsedId);
            if (reading == null) {
                JOptionPane.showMessageDialog(this, "Reading not found");
                return;
            }
            if (isDeleted) {
                refreshReadingsTable();
                JOptionPane.showMessageDialog(this, "Reading deleted successfully");
                return;
            }
            JOptionPane.showMessageDialog(this, "Unable to delete reading");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Entered invalid data");
        }
    }
}
