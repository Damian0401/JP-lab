package interfaces.services;

import models.Address;

import java.util.List;

public interface IAddressService {
    int addAddress(Address address) throws Exception;
    List<Address> getAllAddresses() throws Exception;
    Address getAddressById(int id) throws Exception;
    boolean removeAddressById(int id) throws Exception;
    Integer getIdOfAddress(Address address) throws Exception;
    Address getAddressByFullName(String fullName) throws Exception;
    List<String> getListOfFullNames() throws Exception;
}
