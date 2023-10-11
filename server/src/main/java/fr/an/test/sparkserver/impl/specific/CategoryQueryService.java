package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public class CategoryQueryService extends TableGenericQueryService<CategoryDTO> {
    public CategoryQueryService(AppDatasets appDatasets) {
        super(DbMetadata.Category_, appDatasets.categoryDs());
    }

}
