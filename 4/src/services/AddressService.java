package services;

import interfaces.IDataContext;
import interfaces.services.IAddressService;
import models.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressService implements IAddressService {
    private final IDataContext dataContext;

    public  AddressService(IDataContext dataContext){
        this.dataContext = dataContext;
    }
    @Override
    public int addAddress(Address address) throws Exception {
        var addresses = dataContext.getAddresses();
        var maxId = addresses.stream()
                .map(Address::getId)
                .reduce(Integer::max)
                .orElse(0);
        address.setId(maxId + 1);
        addresses.add(address);
        dataContext.setAddresses(addresses);
        return address.getId();
    }

    @Override
    public List<Address> getAllAddresses() throws Exception {
        return dataContext.getAddresses();
    }

    @Override
    public Address getAddressById(int id) throws Exception {
        var addresses = getAllAddresses();
        return addresses.stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public boolean removeAddressById(int id) throws Exception {
        var addresses = getAllAddresses();
        var addressToRemove = addresses.stream()
                .filter(x -> x.getId() == id)
                .findFirst().orElse(null);
        if (addressToRemove == null)
            return false;
        addresses.remove(addressToRemove);
        dataContext.setAddresses(addresses);
        return true;
    }

    @Override
    public Integer getIdOfAddress(Address address) throws Exception {
        var addresses = getAllAddresses();
        var foundAddress = addresses.stream()
                .filter(x -> x.equals(address))
                .findFirst()
                .orElse(null);
        return foundAddress != null
                ? foundAddress.getId()
                : null;
    }

    @Override
    public Address getAddressByFullName(String fullName) throws Exception {
        var addresses = getAllAddresses();
        return  addresses.stream()
                .filter(x -> (x.getFullName()).equals(fullName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<String> getListOfFullNames() throws Exception {
        var addresses = getAllAddresses();
        return addresses.stream()
                .map(Address::getFullName)
                .collect(Collectors.toList());
    }


}
