package fr.an.test.sparkserver.rest.dto.generic;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class DatasetDTO {
    public List<RowDTO> rows;
    // TOADD ... pagination
}
