package fr.an.test.sparkserver.rest.specific;

import fr.an.test.sparkserver.appdata.specific.SalesQueryService;
import fr.an.test.sparkserver.rest.dto.specific.SalesDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/specific/sales")
@Tag(name="Sales Rest", // do not modify, name used in generated code
    description="rest api for 'Sales' table (fact table)")
public class SalesQueryRestController extends AbstractQueryRestController<SalesDTO> {

    //---------------------------------------------------------------------------------------------

    public SalesQueryRestController(SalesQueryService salesDbService) {
        super(salesDbService);
    }

    //---------------------------------------------------------------------------------------------

}
