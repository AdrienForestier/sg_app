package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.metadata.ColInfosBuilder;
import fr.an.test.sparkserver.metadata.TableInfo;
import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import fr.an.test.sparkserver.rest.dto.specific.SalesDTO;
import fr.an.test.sparkserver.utils.LsUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SalesDbService extends AbstractDbService {

    public static final Encoder<SalesDTO> ENCODER_SalesDTO = Encoders.bean(SalesDTO.class);

    public static final TableInfo<SalesDTO> TABLEINFO_sales;

    static {
        val cols = new ColInfosBuilder<SalesDTO>();
        cols.colInt("salesId", SalesDTO::getSalesId);
        cols.colInt("listId", SalesDTO::getListId);
        cols.colInt("sellerId", SalesDTO::getSellerId);
        cols.colInt("buyerId", SalesDTO::getBuyerId);
        cols.colInt("eventId", SalesDTO::getEventId);
        cols.colInt("dateId", SalesDTO::getDateId);
        cols.colInt("qtySold", SalesDTO::getQtySold);
        cols.colDouble("pricePaid", SalesDTO::getPricePaid);
        cols.colDouble("commission", SalesDTO::getCommission);
        cols.colInstant("saleTime", SalesDTO::getSaleTime);
        TABLEINFO_sales = new TableInfo<SalesDTO>("sales", SalesDTO.class, cols);
    }


    protected final Dataset<Row> salesDs;

    //---------------------------------------------------------------------------------------------

    public SalesDbService(SparkSession sparkSession, AppDatasets appDatasets) {
        super(sparkSession, appDatasets);
        this.salesDs = appDatasets.salesDs();
    }

    //---------------------------------------------------------------------------------------------

    public TableInfoDTO tableInfo() {
        return TABLEINFO_sales.toDTO();
    }

    public List<SalesDTO> findAllDtos() {
        return toDtos(salesDs);
    }

    public List<SalesDTO> firstDtos(int limit) {
        return toDtos(salesDs.limit(limit));
    }

    protected List<SalesDTO> toDtos(Dataset<Row> ds) {
        return ds.as(ENCODER_SalesDTO).collectAsList();
    }

    public List<RowDTO> query(QueryRequestDTO req) {
        val colGetters = LsUtils.map(req.exprs, expr -> TABLEINFO_sales.resolve(expr));
        Dataset<Row> ds = salesDs;
        if (req.limit != 0) {
            ds = ds.limit(req.limit);
        }
        // can not call Dataset.map() .. java.io.NotSerializableException
        val dtos = toDtos(ds);
        return LsUtils.map(dtos, obj -> toRowDTO(obj, colGetters));
    }

}
