package com.park.common.interfaces;

import com.park.common.models.Attraction;
import com.park.common.models.Ticket;

import java.io.IOException;
import java.util.List;

public interface IDataContext {
    List<Attraction> getAttractions() throws IOException;
    Attraction getAttractionById(int id) throws IOException;
    boolean deleteAttractionById(int id) throws IOException;
    boolean updateAttractionById(int id, Attraction attraction) throws IOException;
    int addAttraction(Attraction attraction) throws IOException;
    List<Ticket> getTickets() throws IOException;
    int addTicket(Ticket ticket) throws IOException;
}
