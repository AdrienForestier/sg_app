package fr.an.test.sparkserver.rest.dto.specific;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.time.Instant;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class SalesDTO {

    public int salesId;

    public int listId;
    public int sellerId;
    public int buyerId;
    public int eventId;
    public int dateId;
    public int qtySold;
    public double pricePaid;
    public double commission;
    public Instant saleTime;

}