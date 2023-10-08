package fr.an.test.sparkserver.rest;

import fr.an.test.sparkserver.impl.SalesDbService;
import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import fr.an.test.sparkserver.rest.dto.specific.SalesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/specific/sales")
@Tag(name="Sales Rest", // do not modify, name used in generated code
    description="rest api for 'Sales' table (dimension table)")
public class SalesRestController extends AbstractRestController {

    protected final SalesDbService salesDbService;

    //---------------------------------------------------------------------------------------------

    public SalesRestController(SalesDbService salesDbService) {
        super("/api/specific/sales");
        this.salesDbService = salesDbService;
    }

    //---------------------------------------------------------------------------------------------

    @GetMapping("/tableInfo")
    @Operation(summary = "get table info metadata")
    public TableInfoDTO tableInfo() {
        return salesDbService.tableInfo();
    }

    @GetMapping("")
    @Operation(summary = "list all sales")
    public List<SalesDTO> list() {
        return salesDbService.findAllDtos();
    }

    @GetMapping("/first")
    @Operation(summary = "list first N(default=20) sales")
    public List<SalesDTO> first(@RequestParam(name="limit", defaultValue = "20") int limit) {
        return salesDbService.firstDtos(limit);
    }

    @PutMapping("/query-generic")
    @Operation(summary = "query sales")
    public List<RowDTO> query(@RequestBody QueryRequestDTO req) {
        return salesDbService.query(req);
    }

}
