package utils.table;

import models.Reading;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ReadingsTableModel extends AbstractTableModel {
    private final List<Reading> readings;

    public ReadingsTableModel(List<Reading> readings){
        this.readings = readings;
    }

    @Override
    public int getRowCount() {
        return readings.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column){
            case 0:
                name = "id";
                break;
            case 1:
                name = "readType";
                break;
            case 2:
                name = "value";
                break;
            case 3:
                name = "date";
                break;
            case 4:
                name = "pickupPointId";
                break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reading reading = readings.get(rowIndex);
        Object value = null;
        switch (columnIndex){
            case 0:
                value = reading.getId();
                break;
            case 1:
                value = reading.getReadType();
                break;
            case 2:
                value = reading.getValue();
                break;
            case 3:
                value = reading.getDate();
                break;
            case 4:
                value = reading.getPickupPointId();
                break;
        }
        return value;
    }
}
