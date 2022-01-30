package com.controlcenter.models;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

public class NameTableModel extends AbstractTableModel {
    private final List<String> names;
    private final String name;

    public NameTableModel(String name) {
        names = new LinkedList<>();
        this.name = name;
    }

    public void addName(String name){
        names.add(name);
        fireTableRowsInserted(names.size(), names.size());
    }

    public void deleteName(String name){
        var nameIndex = names.indexOf(name);
        if (nameIndex == -1) return;
        names.remove(nameIndex);
        fireTableRowsDeleted(nameIndex, nameIndex);
    }

    @Override
    public int getRowCount() {
        return names.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int column){
        return column == 0 ? name : "??";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return names.get(rowIndex);
    }
}
