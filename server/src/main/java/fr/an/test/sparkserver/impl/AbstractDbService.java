package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
public abstract class AbstractDbService {

    public static final Encoder<RowDTO> ENCODER_RowDTO = Encoders.bean(RowDTO.class);

    protected final SparkSession sparkSession;

    protected final AppDatasets appDatasets;

    public AbstractDbService(SparkSession sparkSession, AppDatasets appDatasets) {
        this.sparkSession = sparkSession;
        this.appDatasets = appDatasets;
    }


    protected static <T> RowDTO toRowDTO(T obj, List<? extends Function<T,?>> colGetters) {
        int len = colGetters.size();
        Object[] cols = new Object[len];
        for(int i = 0; i < len; i++) {
            cols[i] = colGetters.get(i).apply(obj);
        }
        return new RowDTO(cols);
    }

}
