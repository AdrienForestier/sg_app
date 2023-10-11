package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.metadata.TableInfo;
import fr.an.test.sparkserver.rest.dto.DispatchQueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatchGenericQueryService extends AbstractDbService {

    private final AppDatasets appDatasets;

    public DispatchGenericQueryService(AppDatasets appDatasets) {
        this.appDatasets = appDatasets;
    }

    public TableInfoDTO dispatchTableInfo(String tableName) {
        TableGenericQueryService<?> helper = resolveTableQueryHelper(tableName);
        return helper.tableInfo();
    }

    public List<Object> dispatchFindAllDtos(String tableName) {
        TableGenericQueryService<?> helper = resolveTableQueryHelper(tableName);
        return (List<Object>) helper.findAllDtos();
    }

    public List<Object> dispatchFindFirstDtos(String tableName, int limit) {
        TableGenericQueryService<?> helper = resolveTableQueryHelper(tableName);
        return (List<Object>) helper.firstDtos(limit);
    }

    public List<RowDTO> dispatchQuery(String tableName, QueryRequestDTO req) {
        TableGenericQueryService<?> helper = resolveTableQueryHelper(tableName);
        return helper.query(req);
    }

    private TableGenericQueryService<?> resolveTableQueryHelper(String tableName) {
        TableInfo tableInfo = DbMetadata.DB.tables.get(tableName);
        Dataset<Row> ds = appDatasets.datasetByName(tableName);
        TableGenericQueryService<?> helper = new TableGenericQueryService<>(tableInfo, ds);
        return helper;
    }
}
