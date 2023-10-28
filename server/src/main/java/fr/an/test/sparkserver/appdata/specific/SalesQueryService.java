package fr.an.test.sparkserver.appdata.specific;

import fr.an.test.sparkserver.appdata.AppDatasets;
import fr.an.test.sparkserver.appdata.AppDbMetadata;
import fr.an.test.sparkserver.sql.eval.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.SalesDTO;
import org.springframework.stereotype.Service;

@Service
public class SalesQueryService extends TableGenericQueryService<SalesDTO> {

    public SalesQueryService(AppDatasets appDatasets) {
        super(AppDbMetadata.Sales_, appDatasets.salesDs());
    }

}
