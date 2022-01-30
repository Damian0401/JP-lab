package com.park.common.models.table;

import com.park.common.models.ClientApplication;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class ClientApplicationTableModel extends AbstractTableModel {
    private final List<ClientApplication> clientApplications;
    private final SimpleDateFormat simpleDateFormat;

    public ClientApplicationTableModel(List<ClientApplication> clientApplications) {
        this.clientApplications = clientApplications;
        this.simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    }

    public void addClientApplication(ClientApplication clientApplication){
        clientApplications.add(clientApplication);
        fireTableRowsInserted(clientApplications.size() - 1, clientApplications.size());
    }

    public void clear(){
        clientApplications.clear();
        fireTableDataChanged();
    }

    public void remove(ClientApplication clientApplication){
        var clientApplicationIndex = clientApplications.indexOf(clientApplication);
        if (clientApplicationIndex != -1){
            clientApplications.remove(clientApplicationIndex);
            fireTableRowsDeleted(clientApplicationIndex, clientApplicationIndex);
        }
    }

    @Override
    public String getColumnName(int column){
        String name = "??";
        switch (column){
            case 0:
                name = "port";
                break;
            case 1:
                name = "connectedAt";
                break;
        }
        return name;
    }

    @Override
    public int getRowCount() {
        return clientApplications.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var clientApplication = clientApplications.get(rowIndex);
        Object value = null;
        switch (columnIndex){
            case 0:
                value = clientApplication.getPort();
                break;
            case 1:
                value = simpleDateFormat.format(clientApplication.getConnectedAt());
                break;
        }
        return value;
    }
}
