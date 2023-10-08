package fr.an.test.sparkserver.impl;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public record AppDatasets(
        Dataset<Row> userDs,
        Dataset<Row> eventDs,
        Dataset<Row> categoryDs,
        Dataset<Row> date2008Ds,
        Dataset<Row> venueDs,
        Dataset<Row> listingDs,
        Dataset<Row> salesDs
) {
}
