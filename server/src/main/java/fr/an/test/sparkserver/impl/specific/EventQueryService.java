package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.EventDTO;
import org.springframework.stereotype.Service;

@Service
public class EventQueryService extends TableGenericQueryService<EventDTO> {
    public EventQueryService(AppDatasets appDatasets) {
        super(DbMetadata.Event_, appDatasets.eventDs());
    }

}
