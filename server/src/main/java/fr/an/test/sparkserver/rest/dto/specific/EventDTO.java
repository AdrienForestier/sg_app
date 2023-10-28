package fr.an.test.sparkserver.rest.dto.specific;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class EventDTO {
    // PK
    int eventId;

    // ForeignKey columns
    int venueId;
    int catId;
    int dateId;

    // other columns
    String eventName;
    LocalDateTime startTime;

}
