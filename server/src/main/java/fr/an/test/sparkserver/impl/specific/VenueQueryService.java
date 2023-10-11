package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.VenueDTO;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class VenueQueryService extends TableGenericQueryService<VenueDTO> {

    public VenueQueryService(SparkSession sparkSession, AppDatasets appDatasets) {
        super(sparkSession, DbMetadata.Venue_, appDatasets.venueDs());
    }

}
