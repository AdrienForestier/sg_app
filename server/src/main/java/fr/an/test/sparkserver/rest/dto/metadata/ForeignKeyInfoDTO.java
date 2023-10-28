package fr.an.test.sparkserver.rest.dto.metadata;

import fr.an.test.sparkserver.metadata.JoinRelationship;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class ForeignKeyInfoDTO {

    public JoinRelationship joinRelationship;

}
