package fr.an.test.sparkserver.metadata;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import fr.an.test.sparkserver.rest.dto.generic.DatabaseInfoDTO;
import fr.an.test.sparkserver.utils.LsUtils;

import java.util.Collection;
import java.util.List;

public class DatabaseInfo {

    public final ImmutableMap<String,TableInfo> tables;

    public final ImmutableList<JoinRelationship> otherJoinRelationShips;

    //---------------------------------------------------------------------------------------------

    public DatabaseInfo(Collection<TableInfo> tableInfos, List<JoinRelationship> otherJoinRelationShips) {
        this.tables = ImmutableMap.copyOf(LsUtils.toMap(tableInfos, t -> t.name));
        this.otherJoinRelationShips = ImmutableList.copyOf(otherJoinRelationShips);
    }

    //---------------------------------------------------------------------------------------------

    public DatabaseInfoDTO toDTO() {
        return new DatabaseInfoDTO(
                LsUtils.map(tables.values(), x -> x.toDTO()),
                otherJoinRelationShips // LsUtils.map(otherJoinRelationShips, x -> x.toDTO())
        );
    }

}
