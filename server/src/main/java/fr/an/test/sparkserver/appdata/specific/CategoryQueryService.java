package fr.an.test.sparkserver.appdata.specific;

import fr.an.test.sparkserver.appdata.AppDatasets;
import fr.an.test.sparkserver.appdata.AppDbMetadata;
import fr.an.test.sparkserver.sql.eval.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryQueryService extends TableGenericQueryService<CategoryDTO> {
    public CategoryQueryService(AppDatasets appDatasets) {
        super(AppDbMetadata.Category_, appDatasets.categoryDs());
    }

}
