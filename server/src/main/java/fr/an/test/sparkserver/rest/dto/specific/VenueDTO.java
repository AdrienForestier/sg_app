package fr.an.test.sparkserver.rest.dto.specific;

import lombok.Data;

@Data
public class VenueDTO {

    int venueId;

    String venueName;
    String venueCity;
    String venueState;
    int venueSeats;

}