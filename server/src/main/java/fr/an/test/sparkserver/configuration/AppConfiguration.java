package fr.an.test.sparkserver.configuration;

import fr.an.test.sparkserver.impl.AppDatasets;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Slf4j
public class AppConfiguration {

    @Component
    @ConfigurationProperties(prefix = "app")
    @Getter
    @Setter
    public static class AppProperties {
        protected String baseLocation = "src/main/db-csv";
    }

    @Autowired
    protected AppProperties appProps;


    @Bean
    public SparkSession sparkSession() {
        return SparkSession.builder()
                .appName("testserver")
                .master("local[3]")
                // .enableHiveSupport()
                .getOrCreate();
    }

    @Bean @Autowired
    public AppDatasets appDatasets(SparkSession sparkSession) {
        log.info("loading dimension tables: users, event, category, date2008, venue (should be broadcasted)");
        Dataset<Row> userDs = loadUserDs(sparkSession);
        Dataset<Row> eventDs = loadEventDs(sparkSession);
        Dataset<Row> categoryDs = loadCategoryDs(sparkSession);
        Dataset<Row> date2008Ds = loadDate2008Ds(sparkSession);
        Dataset<Row> venueDs = loadVenueDs(sparkSession);

        log.info("loading facts tables: listings, sales (should not be broadcasted/cached if too big)");
        Dataset<Row> listingDs = loadListingDs(sparkSession);
        Dataset<Row> salesDs = loadSalesDs(sparkSession);

        return new AppDatasets(userDs, eventDs, categoryDs, date2008Ds, venueDs, listingDs, salesDs);
    }

    @NotNull
    private Dataset<Row> loadUserDs(SparkSession sparkSession) {
        Dataset<Row> userDs = sparkSession.read().format("csv")
                .option("delimiter", "|")
                .schema("userid integer not null," +
                        "username string," +
                        "firstname string," +
                        "lastname string," +
                        "city string," +
                        "state string," +
                        "email string," +
                        "phone string," +
                        "likesports boolean," +
                        "liketheatre boolean," +
                        "likeconcerts boolean," +
                        "likejazz boolean," +
                        "likeclassical boolean," +
                        "likeopera boolean," +
                        "likerock boolean," +
                        "likevegas boolean," +
                        "likebroadway boolean," +
                        "likemusicals boolean")
                .load(appProps.getBaseLocation() + "/allusers_pipe.txt");
        userDs.cache();
        // sparkSession.sparkContext().broadcast(userDs);
        long count = userDs.count();
        log.info("users count:" + count);
        return userDs;
    }

    @NotNull
    private Dataset<Row> loadEventDs(SparkSession sparkSession) {
        Dataset<Row> eventDs = sparkSession.read().format("csv")
                .option("delimiter", "|")
                .schema("eventid integer not null," +
                        "venueid int not null," +
                        "catid int not null," +
                        "dateid int not null," +
                        "eventname string," +
                        "starttime timestamp")
                .load(appProps.getBaseLocation() + "/allevents_pipe.txt");
        eventDs.cache();
        long count = eventDs.count();
        log.info("event count:" + count);
        return eventDs;
    }

    @NotNull
    private Dataset<Row> loadCategoryDs(SparkSession sparkSession) {
        Dataset<Row> ds = sparkSession.read().format("csv")
                .option("delimiter", "|")
                .schema("catid int not null," +
                        "catgroup string," +
                        "catname string," +
                        "catdesc string")
                .load(appProps.getBaseLocation() + "/category_pipe.txt");
        ds.cache();
        long count = ds.count();
        log.info("category count:" + count);
        return ds;
    }

    @NotNull
    private Dataset<Row> loadDate2008Ds(SparkSession sparkSession) {
        Dataset<Row> ds = sparkSession.read().format("csv")
                .option("delimiter", "|")
                .schema("dateid smallint not null," +
                        "caldate date not null," +
                        "day string not null," +
                        "week smallint not null," +
                        "month string not null," +
                        "qtr string not null," +
                        "year smallint not null," +
                        "holiday boolean")
                .load(appProps.getBaseLocation() + "/date2008_pipe.txt");
        ds.cache();
        long count = ds.count();
        log.info("date2008 count:" + count);
        return ds;
    }

    @NotNull
    private Dataset<Row> loadVenueDs(SparkSession sparkSession) {
        Dataset<Row> ds = sparkSession.read().format("csv")
                .option("delimiter", "|")
                .schema("venueid smallint not null," +
                        "venuename string," +
                        "venuecity string," +
                        "venuestate string," +
                        "venueseats integer")
                .load(appProps.getBaseLocation() + "/venue_pipe.txt");
        ds.cache();
        long count = ds.count();
        log.info("venue count:" + count);
        return ds;
    }


    @NotNull
    private Dataset<Row> loadListingDs(SparkSession sparkSession) {
        Dataset<Row> ds = sparkSession.read().format("csv")
                .option("delimiter", "|")
                .schema("listid integer not null," +
                        "sellerid integer not null," +
                        "eventid integer not null," +
                        "dateid smallint not null," +
                        "numtickets smallint not null," +
                        "priceperticket double," +
                        "totalprice double," +
                        "listtime timestamp")
                .load(appProps.getBaseLocation() + "/listings_pipe.txt");
//        ds.cache();
//        long count = ds.count();
//        log.info("listings count:" + count);
        return ds;
    }

    @NotNull
    private Dataset<Row> loadSalesDs(SparkSession sparkSession) {
        Dataset<Row> ds = sparkSession.read().format("csv")
                .option("delimiter", "\t")
                .schema("salesid integer not null," +
                        "listid integer not null," +
                        "sellerid integer not null," +
                        "buyerid integer not null," +
                        "eventid integer not null," +
                        "dateid smallint not null," +
                        "qtysold smallint not null," +
                        "pricepaid double," +
                        "commission double," +
                        "saletime timestamp")
                .load(appProps.getBaseLocation() + "/sales_tab.txt");
//        ds.cache();
//        long count = ds.count();
//        log.info("sales count:" + count);
        return ds;
    }

}

