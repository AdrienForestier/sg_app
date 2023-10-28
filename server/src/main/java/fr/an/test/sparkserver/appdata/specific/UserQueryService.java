package fr.an.test.sparkserver.appdata.specific;

import fr.an.test.sparkserver.appdata.AppDatasets;
import fr.an.test.sparkserver.appdata.AppDbMetadata;
import fr.an.test.sparkserver.sql.eval.TableGenericQueryService;
import fr.an.test.sparkserver.rest.dto.specific.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService extends TableGenericQueryService<UserDTO> {
    public UserQueryService(AppDatasets appDatasets) {
        super(AppDbMetadata.User_, appDatasets.userDs());
    }

}
