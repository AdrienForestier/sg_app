package fr.an.test.sparkserver.rest;

import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.rest.dto.generic.DatabaseInfoDTO;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/db",
        produces = "application/json")
public class AppDbRestController extends AbstractRestController {

    //---------------------------------------------------------------------------------------------

    protected AppDbRestController() {
        super("/api/db");
    }

    //---------------------------------------------------------------------------------------------

    @GetMapping(path="/info")
    public DatabaseInfoDTO getDatabaseInfo() {
        return DbMetadata.DB.toDTO();
    }

}
