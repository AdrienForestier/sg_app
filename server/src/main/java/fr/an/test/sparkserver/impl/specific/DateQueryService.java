package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.DateDTO;
import org.springframework.stereotype.Service;

@Service
public class DateQueryService extends TableGenericQueryService<DateDTO> {
    public DateQueryService(AppDatasets appDatasets) {
        super(DbMetadata.Date_, appDatasets.dateDs());
    }

}
