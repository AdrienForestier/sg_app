package fr.an.test.sparkserver.appdata.specific;

import fr.an.test.sparkserver.appdata.AppDatasets;
import fr.an.test.sparkserver.appdata.AppDbMetadata;
import fr.an.test.sparkserver.sql.eval.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.VenueDTO;
import org.springframework.stereotype.Service;

@Service
public class VenueQueryService extends TableGenericQueryService<VenueDTO> {

    public VenueQueryService(AppDatasets appDatasets) {
        super(AppDbMetadata.Venue_, appDatasets.venueDs());
    }

}
