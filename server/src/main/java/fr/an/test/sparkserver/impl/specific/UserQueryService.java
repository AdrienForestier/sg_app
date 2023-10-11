package fr.an.test.sparkserver.impl.specific;

import fr.an.test.sparkserver.impl.AppDatasets;
import fr.an.test.sparkserver.impl.DbMetadata;
import fr.an.test.sparkserver.impl.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService extends TableGenericQueryService<UserDTO> {
    public UserQueryService(AppDatasets appDatasets) {
        super(DbMetadata.User_, appDatasets.userDs());
    }

}
