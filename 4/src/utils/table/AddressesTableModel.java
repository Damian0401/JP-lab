package utils.table;

import models.Address;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AddressesTableModel extends AbstractTableModel {
    private final List<Address> addresses;

    public AddressesTableModel(List<Address> addresses){
        this.addresses = addresses;
    }

    @Override
    public int getRowCount() {
        return addresses.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";
        switch (column){
            case 0:
                name = "id";
                break;
            case 1:
                name = "street";
                break;
            case 2:
                name = "houseNumber";
                break;
            case 3:
                name = "postalCode";
                break;
        }
        return name;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Address address = addresses.get(rowIndex);
        Object value = null;
        switch (columnIndex){
            case 0:
                value = address.getId();
                break;
            case 1:
                value = address.getStreet();
                break;
            case 2:
                value = address.getHouseNumber();
                break;
            case 3:
                value = address.getPostCode();
                break;
        }
        return value;
    }
}
