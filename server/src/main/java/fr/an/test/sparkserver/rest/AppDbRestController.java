package fr.an.test.sparkserver.rest;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/db",
        consumes = "application/json", produces = "application/json")
public class AppDbRestController extends AbstractRestController {

    //---------------------------------------------------------------------------------------------

    protected AppDbRestController() {
        super("/api/db");
    }

    //---------------------------------------------------------------------------------------------

}
