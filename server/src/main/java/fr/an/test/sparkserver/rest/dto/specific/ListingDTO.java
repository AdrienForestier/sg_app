package fr.an.test.sparkserver.rest.dto.specific;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class ListingDTO {

    int listId;

    // FK columns
    int sellerId;
    int eventId;
    int dateId;

    // other
    int numTickets;
    double pricePerTicket;
    double totalPrice;
    LocalDateTime listTime;

}
