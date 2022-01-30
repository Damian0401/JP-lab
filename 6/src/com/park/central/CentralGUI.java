package com.park.central;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.park.central.interfaces.ICentralListener;
import com.park.central.interfaces.ICentralService;
import com.park.common.models.Attraction;
import com.park.common.models.ClientApplication;
import com.park.common.models.table.AttractionTableModel;
import com.park.common.models.table.ClientApplicationTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class CentralGUI extends JFrame implements ICentralListener {
    private JPanel mainPanel;
    private JLabel attractionsLabel;
    private JLabel ticketMachinesLabel;
    private JLabel terminalsLabel;
    private JLabel controlPanelLabel;
    private JLabel portLabel;
    private JLabel currentPortLabel;
    private JLabel statusLabel;
    private JLabel currentStatusLabel;
    private JButton connectButton;
    private JTextField portTextField;
    private JTable attractionsTable;
    private JTable ticketMachinesTable;
    private JTable terminalsTable;
    private JLabel lastActionLabel;
    private JLabel lastActionNameLabel;
    private JButton exitButton;
    private JLabel enterPortLabel;

    private final ICentralService centralService;
    private final DateTimeFormatter dateTimeFormatter;
    private AttractionTableModel attractionTableModel;
    private ClientApplicationTableModel terminalTableModel;
    private ClientApplicationTableModel ticketMachineTableModel;
    private boolean isConnected;

    public CentralGUI(ICentralService centralService) {
        this.centralService = centralService;
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        isConnected = false;
        initializeComponents();
        initializeFrame();
    }

    private void initializeComponents() {
        attractionsTable.getTableHeader().setReorderingAllowed(false);
        terminalsTable.getTableHeader().setReorderingAllowed(false);
        terminalsTable.getTableHeader().setReorderingAllowed(false);
        exitButton.addActionListener(e -> dispose());
        connectButton.addActionListener(e -> tryConnect());
        initializeAttractionTable();
        initializeTicketsMachineTable();
        initializeTerminalsTable();
    }

    private void initializeFrame() {
        setTitle("Central");
        setResizable(false);
        setContentPane(mainPanel);
        setSize(new Dimension(800, 350));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initializeAttractionTable() {
        var allAttractions = centralService.getAllAttractions();
        attractionTableModel = new AttractionTableModel(allAttractions);
        attractionsTable.setModel(attractionTableModel);
    }

    private void initializeTicketsMachineTable() {
        ticketMachineTableModel = new ClientApplicationTableModel(new LinkedList<>());
        ticketMachinesTable.setModel(ticketMachineTableModel);
    }

    private void initializeTerminalsTable() {
        terminalTableModel = new ClientApplicationTableModel(new LinkedList<>());
        terminalsTable.setModel(terminalTableModel);
    }

    private void tryConnect() {
        if (isConnected) {
            centralService.disconnect();
            return;
        }
        performConnection();
    }

    private void performConnection() {
        try {
            var portAsInt = Integer.parseInt(portTextField.getText());
            if (portAsInt < 1024 || portAsInt > 49151) {
                displayMessage("Port must by from range 1024 - 49151");
                return;
            }
            centralService.connect(portAsInt);
        } catch (NumberFormatException e) {
            displayMessage("Invalid host or port");
        }
    }

    public void run() {
        setVisible(true);
        displayMessage("Started");
    }

    @Override
    public void dispose() {
        centralService.disconnect();
        super.dispose();
    }

    @Override
    public void displayMessage(String message) {
        lastActionNameLabel.setText(message + " (" + LocalTime.now().format(dateTimeFormatter) + ")");
    }

    @Override
    public void handleConnection(int port) {
        portLabel.setText(String.valueOf(port));
        statusLabel.setText("Online");
        isConnected = true;
    }

    @Override
    public void handleDisconnection() {
        portLabel.setText("none");
        statusLabel.setText("Offline");
        terminalTableModel.clear();
        ticketMachineTableModel.clear();
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
    public void addTerminal(ClientApplication clientApplication) {
        terminalTableModel.addClientApplication(clientApplication);
    }

    @Override
    public void addTicketMachine(ClientApplication clientApplication) {
        ticketMachineTableModel.addClientApplication(clientApplication);
    }

    @Override
    public void removeTerminal(ClientApplication clientApplication) {
        terminalTableModel.remove(clientApplication);
    }

    @Override
    public void removeTicketMachine(ClientApplication clientApplication) {
        ticketMachineTableModel.remove(clientApplication);
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
        mainPanel.setLayout(new GridLayoutManager(3, 7, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        attractionsLabel = new JLabel();
        attractionsLabel.setText("Attractions");
        mainPanel.add(attractionsLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ticketMachinesLabel = new JLabel();
        ticketMachinesLabel.setText("TicketMachines");
        mainPanel.add(ticketMachinesLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        terminalsLabel = new JLabel();
        terminalsLabel.setText("Terminals");
        mainPanel.add(terminalsLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(1, 3, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        currentPortLabel = new JLabel();
        currentPortLabel.setText("Current port:");
        panel1.add(currentPortLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portLabel = new JLabel();
        portLabel.setText("none");
        panel1.add(portLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        portTextField = new JTextField();
        panel1.add(portTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        enterPortLabel = new JLabel();
        enterPortLabel.setText("Enter port:");
        panel1.add(enterPortLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentStatusLabel = new JLabel();
        currentStatusLabel.setText("Current status:");
        panel1.add(currentStatusLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        statusLabel = new JLabel();
        statusLabel.setText("disconnected");
        panel1.add(statusLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        attractionsTable = new JTable();
        scrollPane1.setViewportView(attractionsTable);
        final JScrollPane scrollPane2 = new JScrollPane();
        mainPanel.add(scrollPane2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ticketMachinesTable = new JTable();
        scrollPane2.setViewportView(ticketMachinesTable);
        final JScrollPane scrollPane3 = new JScrollPane();
        mainPanel.add(scrollPane3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        terminalsTable = new JTable();
        scrollPane3.setViewportView(terminalsTable);
        lastActionNameLabel = new JLabel();
        lastActionNameLabel.setText("none");
        mainPanel.add(lastActionNameLabel, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastActionLabel = new JLabel();
        lastActionLabel.setText("Last action:");
        mainPanel.add(lastActionLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        controlPanelLabel = new JLabel();
        controlPanelLabel.setText("Control panel");
        mainPanel.add(controlPanelLabel, new GridConstraints(0, 3, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        mainPanel.add(exitButton, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        connectButton = new JButton();
        connectButton.setText("ON/OFF");
        mainPanel.add(connectButton, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
