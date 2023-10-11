package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.SalesDTO;
import org.springframework.stereotype.Service;

@Service
public class SalesQueryService extends TableGenericQueryService<SalesDTO> {

    public SalesQueryService(AppDatasets appDatasets) {
        super(DbMetadata.Sales_, appDatasets.salesDs());
    }

}
