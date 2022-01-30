package com.park.ticketmachine;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.park.common.communication.MessageType;
import com.park.common.interfaces.IClientApplicationListener;
import com.park.common.models.Attraction;
import com.park.common.models.Ticket;
import com.park.common.models.table.AttractionTableModel;
import com.park.ticketmachine.abstractions.ATicketMachineService;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class TicketMachineGUI extends JFrame implements IClientApplicationListener {
    private JPanel mainPanel;
    private JTable attractionsTable;
    private JButton buyButton;
    private JButton exitButton;
    private JTextField lastNameTextField;
    private JComboBox<Attraction> selectAttractionComboBox;
    private JTextField firstNameTextField;
    private JTextField serverPortTextField;
    private JButton OnOffButton;
    private JLabel currentStatusLabel;
    private JLabel currentPortLabel;
    private JLabel currentStatusNameLabel;
    private JLabel currentPortNameLabel;
    private JLabel enterServerPortLabel;
    private JLabel lastActionLabel;
    private JLabel lastActionNameLabel;
    private JLabel selectAttractionLabel;
    private JLabel enterFirstNameLabel;
    private JLabel enterLastNameLabel;
    private JTextField serverHostTextField;

    private final ATicketMachineService ticketMachineService;
    private AttractionTableModel attractionTableModel;
    private final DateTimeFormatter dateTimeFormatter;
    private boolean isConnected;

    public TicketMachineGUI(ATicketMachineService ticketMachineService) {
        this.ticketMachineService = ticketMachineService;
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        isConnected = false;
        initializeComponents();
        initializeFrame();
    }

    public void run() {
        displayMessage("Started");
        setVisible(true);
    }

    private void initializeComponents() {
        exitButton.addActionListener(e -> dispose());
        OnOffButton.addActionListener(e -> tryConnect());
        buyButton.addActionListener(e -> tryBuyTicket());
        initializeAttractionTable();
    }

    private void initializeFrame() {
        setTitle("TicketMachine");
        setResizable(false);
        setContentPane(mainPanel);
        setSize(new Dimension(650, 400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initializeAttractionTable() {
        attractionTableModel = new AttractionTableModel(new LinkedList<>());
        attractionsTable.setModel(attractionTableModel);
    }

    private void tryConnect() {
        if (isConnected) {
            ticketMachineService.disconnect();
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
            ticketMachineService.connect(serverHostTextField.getText(), portAsInt);
        } catch (NumberFormatException e) {
            displayMessage("Invalid host or port");
        }
    }

    private void tryBuyTicket() {
        var selectedAttraction = (Attraction) selectAttractionComboBox.getSelectedItem();

        if (selectedAttraction == null || !isConnected) {
            displayMessage("Unable to buy ticket. Connect first.");
            return;
        }

        var ticket = parseTicket(firstNameTextField.getText(), lastNameTextField.getText(), selectedAttraction.getId());

        if (ticket == null) {
            displayMessage("Invalid data. Try again.");
            return;
        }

        ticketMachineService.buyTicket(ticket);
        firstNameTextField.setText("");
        lastNameTextField.setText("");
    }

    private Ticket parseTicket(String firstName, String lastName, int attractionId) {
        if (firstName.isEmpty() || firstName.isBlank() || firstName.equals(MessageType.Separator))
            return null;

        if (lastName.isEmpty() || lastName.isBlank() || lastName.equals(MessageType.Separator))
            return null;

        try {
            return new Ticket(firstName, lastName, attractionId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void dispose() {
        if (isConnected)
            ticketMachineService.disconnect();
        super.dispose();
    }

    @Override
    public void displayMessage(String message) {
        lastActionNameLabel.setText(message + " (" + LocalTime.now().format(dateTimeFormatter) + ")");
    }

    @Override
    public void addAttraction(Attraction attraction) {
        attractionTableModel.addAttraction(attraction);
        selectAttractionComboBox.addItem(attraction);
    }

    @Override
    public void editAttraction(int attractionId, Attraction attraction) {
        attractionTableModel.editAttraction(attractionId, attraction);
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
    public void clearAttractions() {
        attractionTableModel.clear();
        selectAttractionComboBox.removeAllItems();
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
        mainPanel.setLayout(new GridLayoutManager(13, 5, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(0, 0, 12, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        attractionsTable = new JTable();
        scrollPane1.setViewportView(attractionsTable);
        lastActionLabel = new JLabel();
        lastActionLabel.setText("Last action:");
        mainPanel.add(lastActionLabel, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastActionNameLabel = new JLabel();
        lastActionNameLabel.setText("none");
        mainPanel.add(lastActionNameLabel, new GridConstraints(12, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentPortNameLabel = new JLabel();
        currentPortNameLabel.setText("none");
        mainPanel.add(currentPortNameLabel, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentStatusNameLabel = new JLabel();
        currentStatusNameLabel.setText("disconnected");
        mainPanel.add(currentStatusNameLabel, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentStatusLabel = new JLabel();
        currentStatusLabel.setText("CurrentStatus");
        mainPanel.add(currentStatusLabel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentPortLabel = new JLabel();
        currentPortLabel.setText("CurrentPort");
        mainPanel.add(currentPortLabel, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterLastNameLabel = new JLabel();
        enterLastNameLabel.setText("Enter last name:");
        mainPanel.add(enterLastNameLabel, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buyButton = new JButton();
        buyButton.setText("Buy");
        mainPanel.add(buyButton, new GridConstraints(12, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        mainPanel.add(exitButton, new GridConstraints(12, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastNameTextField = new JTextField();
        mainPanel.add(lastNameTextField, new GridConstraints(11, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        selectAttractionLabel = new JLabel();
        selectAttractionLabel.setText("Select attraction");
        mainPanel.add(selectAttractionLabel, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterFirstNameLabel = new JLabel();
        enterFirstNameLabel.setText("Enter first name:");
        mainPanel.add(enterFirstNameLabel, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstNameTextField = new JTextField();
        mainPanel.add(firstNameTextField, new GridConstraints(9, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        selectAttractionComboBox = new JComboBox();
        mainPanel.add(selectAttractionComboBox, new GridConstraints(7, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enterServerPortLabel = new JLabel();
        enterServerPortLabel.setText("Enter server port:");
        mainPanel.add(enterServerPortLabel, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serverPortTextField = new JTextField();
        mainPanel.add(serverPortTextField, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        OnOffButton = new JButton();
        OnOffButton.setText("ON/OFF");
        mainPanel.add(OnOffButton, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Enter server host:");
        mainPanel.add(label1, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        serverHostTextField = new JTextField();
        serverHostTextField.setText("localhost");
        mainPanel.add(serverHostTextField, new GridConstraints(5, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
