package com.park.common.models.table;

import com.park.common.models.Attraction;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AttractionTableModel extends AbstractTableModel {
    private final List<Attraction> attractions;

    public AttractionTableModel(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public void addAttraction(Attraction attraction){
        attractions.add(attraction);
        fireTableRowsInserted(attractions.size() - 1, attractions.size());
    }

    public boolean editAttraction(int attractionId, Attraction attraction){
        attraction.setId(attractionId);
        var indexOfAttractionToEdit = attractions.indexOf(attraction);
        if (indexOfAttractionToEdit == -1)
            return false;
        var attractionToEdit = attractions.get(indexOfAttractionToEdit);
        attractionToEdit.setName(attraction.getName());
        attractionToEdit.setTicketsNumber(attraction.getTicketsNumber());
        attractionToEdit.setPlaceLimit(attraction.getPlaceLimit());
        fireTableRowsInserted(indexOfAttractionToEdit - 1, indexOfAttractionToEdit);
        return true;
    }

    public void clear(){
        attractions.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return attractions.size();
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
                name = "id";
                break;
            case 1:
                name = "name";
                break;
            case 2:
                name = "places";
                break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var attraction = attractions.get(rowIndex);
        Object value = null;
        switch (columnIndex){
            case 0:
                value = attraction.getId();
                break;
            case 1:
                value = attraction.getName();
                break;
            case 2:
                value = attraction.getTicketsNumber() + "/" + attraction.getPlaceLimit();
                break;
        }
        return value;
    }
}
