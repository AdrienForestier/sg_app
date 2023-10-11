package fr.an.test.sparkserver.rest.dto.generic;

import fr.an.test.sparkserver.metadata.ForeignKeyInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
public class TableInfoDTO {

    public String name;

    public List<ColumnInfoDTO> cols;

    public List<String> pkColumns;

    public List<ForeignKeyInfoDTO> foreignKeyInfos;

}
