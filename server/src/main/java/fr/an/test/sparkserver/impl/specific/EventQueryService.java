package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.EventDTO;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class EventQueryService extends TableGenericQueryService<EventDTO> {
    public EventQueryService(SparkSession sparkSession, AppDatasets appDatasets) {
        super(sparkSession, DbMetadata.Event_, appDatasets.eventDs());
    }

}
