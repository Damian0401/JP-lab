package com.park.common.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.park.common.interfaces.IDataContext;
import com.park.common.models.Attraction;
import com.park.common.models.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataContext implements IDataContext {
    private String attractionsFileName = "attractions.json";
    private String ticketsFileName = "tickets.json";
    private String resourcePath = "data/";
    private final ObjectMapper objectMapper;
    private final ObjectWriter objectWriter;

    public DataContext(){
        objectMapper = new ObjectMapper();
        objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
    }

    public DataContext(String resourcePath, String attractionsFileName, String ticketsFileName){
        this();
        this.resourcePath = resourcePath;
        this.attractionsFileName = attractionsFileName;
        this.ticketsFileName = attractionsFileName;
    }

    private void setAttractions(List<Attraction> attractions) throws IOException {
        objectWriter.writeValue(new File(resourcePath + attractionsFileName), attractions);
    }

    private void setTickets(List<Ticket> tickets) throws IOException {
        objectWriter.writeValue(new File(resourcePath + ticketsFileName), tickets);
    }

    @Override
    public List<Attraction> getAttractions() throws IOException {
        return objectMapper.readValue(new File(resourcePath + attractionsFileName), new TypeReference<>() {});
    }

    @Override
    public Attraction getAttractionById(int id) throws IOException {
        var allAttractions = getAttractions();
        return allAttractions.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteAttractionById(int id) throws IOException {
        var allAttractions = getAttractions();
        var isDeleted = allAttractions.removeIf(x -> x.getId() == id);

        if (isDeleted)
            setAttractions(allAttractions);

        return isDeleted;
    }

    @Override
    public boolean updateAttractionById(int id, Attraction attraction) throws IOException {
        var allAttractions = getAttractions();
        var attractionToUpdate = allAttractions.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(null);
        if (attractionToUpdate == null)
            return false;
        mapAttraction(attraction, attractionToUpdate);
        setAttractions(allAttractions);
        return true;
    }

    @Override
    public int addAttraction(Attraction attraction) throws IOException {
        var allAttractions = getAttractions();
        attraction.setId(1);
        if (allAttractions.size() > 0){
            var maxId = allAttractions.stream()
                    .map(Attraction::getId)
                    .max(Integer::compareTo)
                    .orElse(null);
                attraction.setId(maxId + 1);
        }
        allAttractions.add(attraction);
        setAttractions(allAttractions);
        return attraction.getId();
    }

    @Override
    public List<Ticket> getTickets() throws IOException {
        return objectMapper.readValue(new File(resourcePath + ticketsFileName), new TypeReference<>() {});
    }

    @Override
    public int addTicket(Ticket ticket) throws IOException {
        var allTickets = getTickets();
        ticket.setId(1);
        if (allTickets.size() > 0){
            var maxId = allTickets.stream()
                    .map(Ticket::getId)
                    .max(Integer::compareTo)
                    .orElse(null);
            ticket.setId(maxId + 1);
        }
        allTickets.add(ticket);
        setTickets(allTickets);
        return ticket.getId();
    }

    private void mapAttraction(Attraction sourceAttraction, Attraction destinationAttraction){
        destinationAttraction.setName(sourceAttraction.getName());
        destinationAttraction.setPlaceLimit(sourceAttraction.getPlaceLimit());
        destinationAttraction.setTicketsNumber(sourceAttraction.getTicketsNumber());
    }

    private void mapTicket(Ticket sourceTicket, Ticket destinationTicket){
        destinationTicket.setFirstName(sourceTicket.getFirstName());
        destinationTicket.setLastName(sourceTicket.getLastName());
        destinationTicket.setAttractionId(sourceTicket.getAttractionId());
    }
}
