package fr.an.test.sparkserver.rest.dto.expr;

import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor
public class ParseQueryResponseDTO {

    public String parsedToJson;

    public QueryRequestDTO query;

}
