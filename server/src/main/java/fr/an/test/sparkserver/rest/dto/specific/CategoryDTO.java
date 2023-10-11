package fr.an.test.sparkserver.rest.dto.specific;

import lombok.Data;

@Data
public class CategoryDTO {

    int catId;

    String catGroup;
    String catName;
    String catDesc;

}
