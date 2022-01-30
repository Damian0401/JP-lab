package com.controlcenter.models;

import shoppingCarts.data.UpdateInfo;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

public class UpdateInfoTableModel extends AbstractTableModel {
    private final List<UpdateInfo> updateInfo;

    public UpdateInfoTableModel() {
        updateInfo = new LinkedList<>();
    }

    public void addUpdateInfo(UpdateInfo updateInfo){
        this.updateInfo.add(updateInfo);
        fireTableRowsInserted(this.updateInfo.size(), this.updateInfo.size());
    }

    public void deleteUpdateInfo(String stationName){
        var updateInfoToRemove = updateInfo.stream()
                .filter(x -> x.stationName.equals(stationName))
                .findFirst()
                .orElse(null);
        if (updateInfoToRemove == null) return;
        var updateInfoIndex = updateInfo.indexOf(updateInfoToRemove);
        updateInfo.remove(updateInfoIndex);
        fireTableRowsDeleted(updateInfoIndex, updateInfoIndex);
    }

    public void updateUpdateInfo(UpdateInfo updateInfo){
        var updateInfoToUpdate = this.updateInfo.stream()
                .filter(x -> x.stationName.equals(updateInfo.stationName))
                .findFirst()
                .orElse(null);
        if (updateInfoToUpdate == null){
            return;
        }
        updateInfoToUpdate.stationName = updateInfo.stationName;
        updateInfoToUpdate.capacity = updateInfo.capacity;
        updateInfoToUpdate.occupancy = updateInfo.occupancy;
        var updateInfoIndex = this.updateInfo.indexOf(updateInfoToUpdate);
        fireTableRowsUpdated(updateInfoIndex, updateInfoIndex);
    }

    @Override
    public int getRowCount() {
        return updateInfo.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column){
        String name = "??";
        switch (column){
            case 0:
                name = "stationName";
                break;
            case 1:
                name = "capacity";
                break;
            case 2:
                name = "occupancy";
                break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var updateInfo = this.updateInfo.get(rowIndex);
        Object value = null;
        switch (columnIndex){
            case 0:
                value = updateInfo.stationName;
                break;
            case 1:
                value = updateInfo.capacity;
                break;
            case 2:
                value = updateInfo.occupancy;
                break;
        }
        return value;
    }
}
