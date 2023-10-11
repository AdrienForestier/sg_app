package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.metadata.DatabaseInfo;
import fr.an.test.sparkserver.metadata.TableInfo;
import fr.an.test.sparkserver.metadata.TableInfoBuilder;
import fr.an.test.sparkserver.rest.dto.specific.SalesDTO;

import java.util.Arrays;

public class DbMetadata {

    public static final TableInfo<SalesDTO> Sales_ = new TableInfoBuilder<>("Sales", SalesDTO.class,
            cols -> {
                // PrimaryKey column(s)
                cols.colInt("salesId", SalesDTO::getSalesId);
                // ForeignKey (dimension) columns
                cols.colInt("listId", SalesDTO::getListId);
                cols.colInt("sellerId", SalesDTO::getSellerId);
                cols.colInt("buyerId", SalesDTO::getBuyerId);
                cols.colInt("eventId", SalesDTO::getEventId);
                cols.colInt("dateId", SalesDTO::getDateId);
                // measures columns
                cols.colInt("qtySold", SalesDTO::getQtySold);
                cols.colDouble("pricePaid", SalesDTO::getPricePaid);
                cols.colDouble("commission", SalesDTO::getCommission);
                cols.colInstant("saleTime", SalesDTO::getSaleTime);
            },
            "salesId",
            fks -> {
                fks.fk("listId", "Listing", "listId");
                fks.fk("sellerId", "User", "userId");
                fks.fk("buyerId", "User", "userId");
                fks.fk("eventId", "Event", "eventId");
                fks.fk("dateId", "Date", "dateId");
            }
    ).build();

    public static final DatabaseInfo DB = new DatabaseInfo(
            Arrays.asList(Sales_),
            Arrays.asList()
    );

}
