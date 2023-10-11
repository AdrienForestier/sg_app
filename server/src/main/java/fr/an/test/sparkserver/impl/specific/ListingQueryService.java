package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.ListingDTO;
import org.springframework.stereotype.Service;

@Service
public class ListingQueryService extends TableGenericQueryService<ListingDTO> {
    public ListingQueryService(AppDatasets appDatasets) {
        super(DbMetadata.Listing_, appDatasets.listingDs());
    }

}
