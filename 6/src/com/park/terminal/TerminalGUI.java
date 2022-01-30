package com.park.terminal;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.park.common.communication.MessageType;
import com.park.common.interfaces.IClientApplicationListener;
import com.park.common.models.Attraction;
import com.park.common.models.table.AttractionTableModel;
import com.park.terminal.abstractions.ATerminalService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class TerminalGUI extends JFrame implements IClientApplicationListener {
    private JPanel mainPanel;
    private JTextField attractionNameTextField;
    private JTextField placesNumberTextField;
    private JTable attractionsTable;
    private JButton addButton;
    private JButton exitButton;
    private JTextField serverPortTextField;
    private JButton OnOffButton;
    private JLabel lastActionLabel;
    private JLabel lastActionNameLabel;
    private JLabel enterAttractionNameLabel;
    private JLabel enterNumberOfPlacesLabel;
    private JLabel currentStatusLabel;
    private JLabel currentPortLabel;
    private JLabel currentStatusNameLabel;
    private JLabel currentPortNameLabel;
    private JLabel enterServerPortLabel;
    private JTextField serverHostTextField;
    private JLabel enterServerHostLabel;

    private final ATerminalService terminalService;
    private AttractionTableModel attractionTableModel;
    private final DateTimeFormatter dateTimeFormatter;
    private boolean isConnected;

    public TerminalGUI(ATerminalService terminalService) {
        this.terminalService = terminalService;
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        isConnected = false;
        initializeComponents();
        initializeFrame();
    }

    public void run() {
        setVisible(true);
        displayMessage("Started");
    }

    private void initializeAttractionTable() {
        attractionTableModel = new AttractionTableModel(new LinkedList<>());
        attractionsTable.setModel(attractionTableModel);
    }

    private void initializeComponents() {
        exitButton.addActionListener(e -> dispose());
        addButton.addActionListener(e -> tryAddAttraction());
        OnOffButton.addActionListener(e -> tryConnect());
        initializeAttractionTable();
    }

    private void tryAddAttraction() {
        if (!isConnected) {
            displayMessage("Unable to add attraction. Connect first");
            return;
        }

        var attraction = parseAttraction(attractionNameTextField.getText(), placesNumberTextField.getText());

        if (attraction == null) {
            displayMessage("Invalid data. Try again");
            return;
        }

        terminalService.addAttraction(attraction);
        attractionNameTextField.setText("");
        placesNumberTextField.setText("");
    }

    private void initializeFrame() {
        setTitle("Terminal");
        setResizable(false);
        setContentPane(mainPanel);
        setSize(new Dimension(700, 350));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void tryConnect() {
        if (isConnected) {
            terminalService.disconnect();
            return;
        }
        performConnection();
    }

    private void performConnection() {
        try {
            var portAsInt = Integer.parseInt(serverPortTextField.getText());
            if (portAsInt < 1024 || portAsInt > 49151) {
                displayMessage("Port must by from range 1024 - 49151");
                return;
            }
            terminalService.connect(serverHostTextField.getText(), portAsInt);
        } catch (NumberFormatException e) {
            displayMessage("Invalid host or port");
        }
    }

    private Attraction parseAttraction(String name, String portAsString) {
        if (name.equals(MessageType.Separator) || name.isEmpty() || name.isBlank())
            return null;
        try {
            var portAsInt = Integer.parseInt(portAsString);
            return portAsInt > 0 ? new Attraction(name, portAsInt) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void dispose() {
        if (isConnected)
            terminalService.disconnect();
        super.dispose();
    }

    @Override
    public void displayMessage(String message) {
        lastActionNameLabel.setText(message + " (" + LocalTime.now().format(dateTimeFormatter) + ")");
    }

    @Override
    public void handleConnection(int port, String serverName) {
        currentPortNameLabel.setText(String.valueOf(port));
        currentStatusNameLabel.setText("connected to " + serverName);
        isConnected = true;
    }

    @Override
    public void handleDisconnection() {
        currentStatusNameLabel.setText("disconnected");
        currentPortNameLabel.setText("none");
        isConnected = false;
    }

    @Override
    public void addAttraction(Attraction attraction) {
        attractionTableModel.addAttraction(attraction);
    }

    @Override
    public void editAttraction(int attractionId, Attraction attraction) {
        attractionTableModel.editAttraction(attractionId, attraction);
    }

    @Override
    public void clearAttractions() {
        attractionTableModel.clear();
    }

    @Override
    public void popUpMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
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
        mainPanel.setLayout(new GridLayoutManager(11, 5, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(0, 0, 10, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        attractionsTable = new JTable();
        scrollPane1.setViewportView(attractionsTable);
        lastActionLabel = new JLabel();
        lastActionLabel.setText("Last action:");
        mainPanel.add(lastActionLabel, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentStatusLabel = new JLabel();
        currentStatusLabel.setText("CurrentStatus:");
        mainPanel.add(currentStatusLabel, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        attractionNameTextField = new JTextField();
        mainPanel.add(attractionNameTextField, new GridConstraints(7, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        enterNumberOfPlacesLabel = new JLabel();
        enterNumberOfPlacesLabel.setText("Enter number of places:");
        mainPanel.add(enterNumberOfPlacesLabel, new GridConstraints(8, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        placesNumberTextField = new JTextField();
        mainPanel.add(placesNumberTextField, new GridConstraints(9, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lastActionNameLabel = new JLabel();
        lastActionNameLabel.setText("none");
        mainPanel.add(lastActionNameLabel, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        mainPanel.add(addButton, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        mainPanel.add(exitButton, new GridConstraints(10, 3, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentPortLabel = new JLabel();
        currentPortLabel.setText("Current port:");
        mainPanel.add(currentPortLabel, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentPortNameLabel = new JLabel();
        currentPortNameLabel.setText("none");
        mainPanel.add(currentPortNameLabel, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterAttractionNameLabel = new JLabel();
        enterAttractionNameLabel.setText("Enter attraction name:");
        mainPanel.add(enterAttractionNameLabel, new GridConstraints(6, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentStatusNameLabel = new JLabel();
        currentStatusNameLabel.setText("disconnected");
        mainPanel.add(currentStatusNameLabel, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serverPortTextField = new JTextField();
        mainPanel.add(serverPortTextField, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        OnOffButton = new JButton();
        OnOffButton.setText("ON/OFF");
        mainPanel.add(OnOffButton, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterServerPortLabel = new JLabel();
        enterServerPortLabel.setText("Enter server port:");
        mainPanel.add(enterServerPortLabel, new GridConstraints(2, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterServerHostLabel = new JLabel();
        enterServerHostLabel.setText("Enter server host:");
        mainPanel.add(enterServerHostLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serverHostTextField = new JTextField();
        serverHostTextField.setText("localhost");
        mainPanel.add(serverHostTextField, new GridConstraints(5, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
