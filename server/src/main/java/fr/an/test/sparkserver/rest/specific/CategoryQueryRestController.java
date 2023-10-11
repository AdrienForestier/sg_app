package fr.an.test.sparkserver.rest.specific;

import fr.an.test.sparkserver.impl.specific.CategoryQueryService;
import fr.an.test.sparkserver.impl.specific.DateQueryService;
import fr.an.test.sparkserver.rest.dto.specific.CategoryDTO;
import fr.an.test.sparkserver.rest.dto.specific.DateDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/specific/category")
@Tag(name="Category Rest", // do not modify, name used in generated code
    description="rest api for 'Category' table (dimension table)")
public class CategoryQueryRestController extends AbstractQueryRestController<CategoryDTO> {

    //---------------------------------------------------------------------------------------------

    public CategoryQueryRestController(CategoryQueryService queryService) {
        super(queryService);
    }

    //---------------------------------------------------------------------------------------------

}
