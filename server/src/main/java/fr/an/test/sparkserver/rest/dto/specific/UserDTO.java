package fr.an.test.sparkserver.rest.dto.specific;

import lombok.Data;

@Data
public class UserDTO {

    // PK
    int userId;

    // other
    String userName;
    String firstName;
    String lastName;
    String city;
    String state;
    String email;
    String phone;
    boolean likeSports;
    boolean likeTheatre;
    boolean likeConcerts;
    boolean likeJazz;
    boolean likeClassical;
    boolean likeOpera;
    boolean likeRock;
    boolean likeVegas;
    boolean likeBroadway;
    boolean likeMusicals;

}
