package utils.table;

import models.Address;
import models.PickupPoint;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PickupPointTableModel extends AbstractTableModel {
    private final List<PickupPoint> pickupPoints;

    public PickupPointTableModel(List<PickupPoint> pickupPoints){
        this.pickupPoints = pickupPoints;
    }

    @Override
    public int getRowCount() {
        return pickupPoints.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column){
            case 0:
                name = "id";
                break;
            case 1:
                name = "mediumType";
                break;
            case 2:
                name = "addressId";
                break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PickupPoint pickupPoint = pickupPoints.get(rowIndex);
        Object value = null;
        switch (columnIndex){
            case 0:
                value = pickupPoint.getId();
                break;
            case 1:
                value = pickupPoint.getMediumType();
                break;
            case 2:
                value = pickupPoint.getAddressId();
                break;
        }
        return value;
    }
}
