package fr.an.test.sparkserver;

import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/db")
public class AppDbRestController {

    @Autowired
    protected SparkSession spark;

}
