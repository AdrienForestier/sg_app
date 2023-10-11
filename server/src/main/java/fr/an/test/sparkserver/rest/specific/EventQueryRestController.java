package fr.an.test.sparkserver.rest.specific;

import fr.an.test.sparkserver.impl.specific.CategoryQueryService;
import fr.an.test.sparkserver.impl.specific.EventQueryService;
import fr.an.test.sparkserver.rest.dto.specific.CategoryDTO;
import fr.an.test.sparkserver.rest.dto.specific.EventDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/specific/event")
@Tag(name="Event Rest", // do not modify, name used in generated code
    description="rest api for 'Event' table (dimension table)")
public class EventQueryRestController extends AbstractQueryRestController<EventDTO> {

    //---------------------------------------------------------------------------------------------

    public EventQueryRestController(EventQueryService queryService) {
        super(queryService);
    }

    //---------------------------------------------------------------------------------------------

}
