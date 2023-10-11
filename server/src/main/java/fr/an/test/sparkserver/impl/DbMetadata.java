package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.metadata.DatabaseInfo;
import fr.an.test.sparkserver.metadata.TableInfo;
import fr.an.test.sparkserver.metadata.TableInfoBuilder;
import fr.an.test.sparkserver.rest.dto.specific.*;
import org.apache.spark.sql.execution.streaming.state.package$;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

public class DbMetadata {

    public static final TableInfo<UserDTO> User_ = new TableInfoBuilder<>("User", UserDTO.class,
            cols -> {
                // PrimaryKey column(s)
                cols.colInt("userId", UserDTO::getUserId);
                // othercolumns
                cols.colString("userName", UserDTO::getUserName);
                cols.colString("firstName", UserDTO::getFirstName);
                cols.colString("lastName", UserDTO::getLastName);
                cols.colString("city", UserDTO::getCity);
                cols.colString("state", UserDTO::getState);
                cols.colString("email", UserDTO::getEmail);
                cols.colString("phone", UserDTO::getPhone);
                cols.colBoolean("likeSports", UserDTO::getLikeSports);
                cols.colBoolean("likeTheatre", UserDTO::getLikeTheatre);
                cols.colBoolean("likeConcerts", UserDTO::getLikeConcerts);
                cols.colBoolean("likeJazz", UserDTO::getLikeJazz);
                cols.colBoolean("likeClassical", UserDTO::getLikeClassical);
                cols.colBoolean("likeOpera", UserDTO::getLikeOpera);
                cols.colBoolean("likeRock", UserDTO::getLikeRock);
                cols.colBoolean("likeVegas", UserDTO::getLikeVegas);
                cols.colBoolean("likeBroadway", UserDTO::getLikeBroadway);
                cols.colBoolean("likeMusicals", UserDTO::getLikeMusicals);
            },
            "",
            fks -> {}
    ).build();


    public static final TableInfo<EventDTO> Event_ = new TableInfoBuilder<>("Event", EventDTO.class,
            cols -> {
                // PrimaryKey column(s)
                cols.colInt("eventId", EventDTO::getEventId);
                // ForeignKey (dimension) columns
                cols.colInt("venueId", EventDTO::getVenueId);
                cols.colInt("catId", EventDTO::getCatId);
                cols.colInt("dateId", EventDTO::getDateId);
                // other columns
                cols.colString("eventName", EventDTO::getEventName);
                cols.colInstant("startTime", EventDTO::getStartTime);
            },
            "",
            fks -> {
                fks.fk("venueId", "Venue", "venueId");
                fks.fk("catId", "Category", "catId");
                fks.fk("dateId", "Date", "dateId");
            }
    ).build();

    public static final TableInfo<CategoryDTO> Category_ = new TableInfoBuilder<>("Category", CategoryDTO.class,
            cols -> {
                // PrimaryKey column(s)
                cols.colInt("catId", CategoryDTO::getCatId);
                // other columns
                cols.colString("catGroup", CategoryDTO::getCatGroup);
                cols.colString("catName", CategoryDTO::getCatName);
                cols.colString("catDesc", CategoryDTO::getCatDesc);
            },
            "catId",
            fks -> {}
    ).build();

    public static final TableInfo<DateDTO> Date_ = new TableInfoBuilder<>("Date", DateDTO.class,
            cols -> {
                // PrimaryKey column(s)
                cols.colInt("dateId", DateDTO::getDateId);
                // other columns
                cols.col("calDate", LocalDate.class, DateDTO::getCalDate);
                cols.colString("day", DateDTO::getDay);
                cols.colInt("week", DateDTO::getWeek);
                cols.colString("month", DateDTO::getMonth);
                cols.colString("qtr", DateDTO::getQtr);
                cols.colInt("year", DateDTO::getYear);
                cols.colBoolean("holiday", DateDTO::isHoliday);
            },
            "dateId",
            fks -> {}
    ).build();

    public static final TableInfo<VenueDTO> Venue_ = new TableInfoBuilder<>("Venue", VenueDTO.class,
            cols -> {
                // PrimaryKey column(s)
                cols.colInt("venueId", VenueDTO::getVenueId);
                // other columns
                cols.colString("venueName", VenueDTO::getVenueName);
                cols.colString("venueCity", VenueDTO::getVenueCity);
                cols.colString("venueState", VenueDTO::getVenueState);
                cols.colInt("venueSeats", VenueDTO::getVenueSeats);
            },
            "venueId",
            fks -> {}
    ).build();

    public static final TableInfo<ListingDTO> Listing_ = new TableInfoBuilder<>("Listing", ListingDTO.class,
            cols -> {
                // PrimaryKey column(s)
                cols.colInt("listId", ListingDTO::getListId);
                // FK columns
                cols.colInt("sellerId", ListingDTO::getSellerId);
                cols.colInt("eventId", ListingDTO::getEventId);
                cols.colInt("dateId", ListingDTO::getDateId);
                // other
                cols.colInt("numTickets", ListingDTO::getNumTickets);
                cols.colDouble("pricePerTicket", ListingDTO::getPricePerTicket);
                cols.colDouble("totalPrice", ListingDTO::getTotalPrice);
                cols.colInstant("listTime", ListingDTO::getListTime);
            },
            "listId",
            fks -> {
                fks.fk("sellerId", "User", "userId");
                fks.fk("eventId", "Event", "eventId");
                fks.fk("dateId", "Date", "dateId");
            }
    ).build();

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
            Arrays.asList(
                    User_, Event_, Category_, Date_, Venue_, // dimension tables
                    Listing_, Sales_ // fact tables
            ),
            Arrays.asList()
    );

}
