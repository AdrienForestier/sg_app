package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.metadata.TableInfo;
import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import fr.an.test.sparkserver.utils.LsUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;

import java.util.List;

@Slf4j
public class TableGenericQueryService<T> extends AbstractDbService {

    protected final TableInfo<T> tableInfo;

    protected final Dataset<Row> tableDataset;

    protected final Encoder<T> sparkEncoder;

    //---------------------------------------------------------------------------------------------

    public TableGenericQueryService(TableInfo<T> tableInfo, Dataset<Row> tableDataset) {
        this.tableInfo = tableInfo;
        this.tableDataset = tableDataset;
        this.sparkEncoder = Encoders.bean(tableInfo.objectClass);
    }

    //---------------------------------------------------------------------------------------------

    public TableInfoDTO tableInfo() {
        return tableInfo.toDTO();
    }

    public List<T> findAllDtos() {
        return toDtos(tableDataset);
    }

    public List<T> firstDtos(int limit) {
        return toDtos(tableDataset.limit(limit));
    }

    protected List<T> toDtos(Dataset<Row> ds) {
        return ds.as(sparkEncoder).collectAsList();
    }

    public List<RowDTO> query(QueryRequestDTO req) {
        val colGetters = LsUtils.map(req.exprs, expr -> tableInfo.resolve(expr));
        Dataset<Row> ds = tableDataset;
        if (req.limit != 0) {
            ds = ds.limit(req.limit);
        }
        // can not call Dataset.map() .. java.io.NotSerializableException
        val dtos = toDtos(ds);
        return LsUtils.map(dtos, obj -> toRowDTO(obj, colGetters));
    }

}
