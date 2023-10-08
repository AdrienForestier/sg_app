package fr.an.test.sparkserver.rest.dto;

import java.util.List;

public class QueryRequestDTO {

    public int limit;

    public List<String> exprs;
    // TOADD where conditions..
    // TOADD sorting columns conditions
    // TOADD groupBy conditions
    // TOADD having condtiions
}
