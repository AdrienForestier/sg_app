package fr.an.test.sparkserver.rest;

import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.rest.dto.expr.ParseRequestDTO;
import fr.an.test.sparkserver.rest.dto.expr.ParseResponseDTO;
import fr.an.test.sparkserver.rest.dto.generic.DatabaseInfoDTO;
import fr.an.test.sparkserver.sqlexpr.SqlExprParserService;
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
        return DbMetadata.DB.toDTO();
    }

    @PostMapping(path="test-parse-expr")
    public ParseResponseDTO parseExprRequest(@RequestBody ParseRequestDTO req) {
        return sqlExprService.parseExprRequest(req);
    }

}
