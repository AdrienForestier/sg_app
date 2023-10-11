package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.VenueDTO;
import org.springframework.stereotype.Service;

@Service
public class VenueQueryService extends TableGenericQueryService<VenueDTO> {

    public VenueQueryService(AppDatasets appDatasets) {
        super(DbMetadata.Venue_, appDatasets.venueDs());
    }

}
