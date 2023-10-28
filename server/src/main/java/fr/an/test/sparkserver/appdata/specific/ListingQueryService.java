package fr.an.test.sparkserver.appdata.specific;

import fr.an.test.sparkserver.appdata.AppDatasets;
import fr.an.test.sparkserver.appdata.AppDbMetadata;
import fr.an.test.sparkserver.sql.eval.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.ListingDTO;
import org.springframework.stereotype.Service;

@Service
public class ListingQueryService extends TableGenericQueryService<ListingDTO> {
    public ListingQueryService(AppDatasets appDatasets) {
        super(AppDbMetadata.Listing_, appDatasets.listingDs());
    }

}
