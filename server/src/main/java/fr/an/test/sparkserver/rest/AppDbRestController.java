package fr.an.test.sparkserver.rest;

import fr.an.test.sparkserver.appdata.AppDbMetadata;
import fr.an.test.sparkserver.rest.dto.expr.ParseQueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.expr.ParseQueryResponseDTO;
import fr.an.test.sparkserver.rest.dto.metadata.DatabaseInfoDTO;
import fr.an.test.sparkserver.sql.analysis.SqlExprParserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/db",
        produces = "application/json")
public class AppDbRestController extends AbstractRestController {

    protected final SqlExprParserService sqlExprService;

    //---------------------------------------------------------------------------------------------

    public AppDbRestController(SqlExprParserService sqlExprService) {
        super("/api/db");
        this.sqlExprService = sqlExprService;
    }

    //---------------------------------------------------------------------------------------------

    @GetMapping(path="/info")
    public DatabaseInfoDTO getDatabaseInfo() {
        return AppDbMetadata.DB.toDTO();
    }

    @PostMapping(path="/test-parse-query")
    public ParseQueryResponseDTO parseQueryRequest(@RequestBody ParseQueryRequestDTO req) {
        return sqlExprService.parseQueryRequest(req);
    }

}
