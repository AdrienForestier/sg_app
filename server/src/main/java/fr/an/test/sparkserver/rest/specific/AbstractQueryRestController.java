
package fr.an.test.sparkserver.rest.specific;

import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.impl.specific.SalesQueryService;
import fr.an.test.sparkserver.rest.AbstractRestController;
import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.QuerySimpleTableColumnsParamsDTO;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import fr.an.test.sparkserver.rest.dto.specific.SalesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class AbstractQueryRestController<T> extends AbstractRestController {

    protected final TableGenericQueryService<T> queryService;

    //---------------------------------------------------------------------------------------------

    public AbstractQueryRestController(TableGenericQueryService<T> queryService) {
        super("/api/specific/" + queryService.tableInfo().name.toLowerCase());
        this.queryService = queryService;
    }

    //---------------------------------------------------------------------------------------------

    @GetMapping("/tableInfo")
    @Operation(summary = "get table info metadata")
    public TableInfoDTO tableInfo() {
        return queryService.tableInfo();
    }

    @GetMapping("")
    @Operation(summary = "list all")
    public List<T> list() {
        return queryService.findAllDtos();
    }

    @GetMapping("/first")
    @Operation(summary = "list first N(default=20)")
    public List<T> first(@RequestParam(name="limit", defaultValue = "20") int limit) {
        return queryService.firstDtos(limit);
    }

    @PutMapping("/query-generic")
    @Operation(summary = "query")
    public List<RowDTO> query(@RequestBody QuerySimpleTableColumnsParamsDTO req) {
        return queryService.querySimpleCols(req);
    }

}
