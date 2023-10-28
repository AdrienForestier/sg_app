package fr.an.test.sparkserver.rest.dto.metadata;

import fr.an.test.sparkserver.metadata.JoinRelationship;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor
public class DatabaseInfoDTO {

    public List<TableInfoDTO> tables;

    public List<JoinRelationship> otherJoinRelationShips;

}
