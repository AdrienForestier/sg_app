package fr.an.test.sparkserver.rest.dto.generic;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class TableInfoDTO {
    public String name;
    public List<ColumnInfoDTO> cols;
}
